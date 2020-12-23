package code.controller;

import code.domain.Applicant;
import code.domain.Applyforlocation;
import code.domain.Company;
import code.domain.Position;
import code.service.IApplicantService;
import code.service.IApplyforlocationService;
import code.service.ICompanyService;
import code.service.IPositionService;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/company")
public class ICompanyController {

	@Autowired
	private IApplicantService applicantService;

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IPositionService positionService;

	@Autowired
	private IApplyforlocationService applyforlocationService;

	//主页面
	@ResponseBody
	@RequestMapping(value ="/showTheData" ,produces = "text/json; charset=utf-8")
	public String showTheData(@RequestParam(value = "cid")Integer cid, Model model){
		//根据用户id查询用户身份
		Company company = companyService.findByCid(cid);
		List list = new ArrayList();
		List<Position> positionList = positionService.findByPcid(cid);
		int PositionCount = positionList.size();
		int ResumeCount=0;
		int NoSureResumeCount=0;
		int PassResumeCount=0;
		List<Integer> NoSurePositionCountList = new ArrayList();
		for(int i = 0; i < positionList.size(); i++){
			ResumeCount += applyforlocationService.findByApid(positionList.get(i).getPid()).size();
			NoSureResumeCount += applyforlocationService.PositionNoHavePass(positionList.get(i).getPid()).size();
			PassResumeCount += applyforlocationService.PositionHavePass(positionList.get(i).getPid()).size();
			NoSurePositionCountList.add(applyforlocationService.PositionNoHavePass(positionList.get(i).getPid()).size());
		}
		list.add(company);
		list.add(PositionCount);
		list.add(ResumeCount);
		list.add(NoSureResumeCount);
		list.add(PassResumeCount);
		list.add(positionList);
		list.add(NoSurePositionCountList);
		String myList = new Gson().toJson(list);
		return myList;
	}

	//跳转到发布职位页面
	@RequestMapping("/jumpToPostJob")
	public String jumpToPostJob(Integer cid,Model model){
		model.addAttribute("cid",cid);
		return "PostJob";
	}

	//	提交题目
	@RequestMapping("/addPosition")
	public String addPosition(Position position){
		System.out.println("保存职位");
		System.out.println(position.toString());
//		positionService.savePosition(position);
		return "applicant_homepage";
	}









	@RequestMapping(value ="/jumpToCompanyData")
	public String jumpToOne(String cid,Model model){
		model.addAttribute("cid",cid);
		return "company_information_manage";
	}


	@ResponseBody
	@RequestMapping(value ="/havePosition",produces = "text/json; charset=utf-8")
	public String havePosition(String cid){
		System.out.println(cid);
		Integer id =Integer.valueOf(cid);
		List<Position> positions = positionService.findByPcid(id);
		String myplaces = new Gson().toJson(positions);
		System.out.println(positions);
		return myplaces;
	}

	@RequestMapping(value = "jumpToResume")
	public String jumpToResume(String pid,String cid,Model model){
		model.addAttribute("pid",pid);
		model.addAttribute("cid",cid);
		Integer id =Integer.valueOf(pid);
		model.addAttribute("position",positionService.findByPid(id));
		return "resume";
	}

	@RequestMapping(value = "jumpToSuccess")
	public String jumpToSucess(String pid,Model model){
		model.addAttribute("pid",pid);
		return "success";
	}

	@RequestMapping(value = "updateMyData")
	public String updateMyData(Company company,Model model){
		companyService.updateCompany(company);
		System.out.println(company.toString());
		System.out.println(company.getCid());
		model.addAttribute("cid",company.getCid());
		return "company_information_manage";
	}

	@ResponseBody
	@RequestMapping(value = "haveApplicant",produces = "text/json; charset=utf-8")
	public String haveApplicant(String pid,Model model){
		Integer id =Integer.valueOf(pid);
		List<Applyforlocation> applyforlocations = applyforlocationService.findByApid(id);
		model.addAttribute("position",positionService.findByPid(id));
		List<Applicant> applicants = new ArrayList<>();
		for(int i = 0; i < applyforlocations.size(); i++){
			applicants.add(applicantService.findById(applyforlocations.get(i).getAaid()));
		}
		List list = new ArrayList();
		list.add(applicants);
		list.add(applyforlocations);
		String endList = new Gson().toJson(list);
		System.out.println(endList);
		return endList;
	}


	@ResponseBody
	@RequestMapping("/fileDownload")
	public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, @RequestParam(value="aaid") Integer aaid, @RequestParam(value="apid")Integer apid) throws Exception{
		String path = request.getServletContext().getRealPath("/uploads/");
		Applyforlocation applyforlocation = applyforlocationService.findByPAid(apid, aaid);
		String filename =applyforlocation.getAfilepath();
		File file = new File(path+File.separator+filename);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment",filename);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
	}
}
