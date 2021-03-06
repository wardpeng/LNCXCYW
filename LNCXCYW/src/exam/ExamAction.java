package exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import GlobalInfo.NewsPageInfo;
import baseaction.BasePageInfoAction;
import cache.Cache;
import dao.ExamDao;
import dao.ExamPaperDao;
import mode.ExamEvalution;
import mode.ExamOption;
import mode.ExamPaper;
import mode.ExamTitle;
import mode.News;

/*
 * 该类主要对应于用户进行素质测评 相关的action
 */
public class ExamAction extends BasePageInfoAction {
	
	private static final long serialVersionUID = 1L;
	private String paperName;//表明是哪一个试卷,对应试卷描述
	private int countOftitle;//抽取该测试类型题目数量
	private List<ExamTitle> listOftitle;//存储抽取的试卷
	private List<List<ExamOption>> optionsOfTitle;//题目的选项列表
	private List<Integer> checkedOptionList;     //答题记录
	private List<ExamPaper> examPaperList;//所有试题类型的列表
	private String evaluate;//评价
	private String paperDescription;
	
	private List<News> newestNewsList;//每日推荐的新闻列表
	private List<News> pictureNewsList;//焦点图片新闻列表
	private List<News> hotestNewsList;//排行榜新闻列表
	
	private final String examKey = "exam";//exam存入session中后对应的键值
	


	
	public Exam getExamByPaperName(ExamPaper exampaper){
		System.out.println("createExam:");
		Exam newExam = new Exam(exampaper);//创建新试卷
		ExamDao ed = new ExamDao();
		
		//获取该试卷的所有评价标准
		List<ExamEvalution> examEvalutionList = ed.getAllExamEvalutionByPaper(exampaper);
		newExam.addAllExamEvalution(examEvalutionList);
		
		//获取该类型的全部题目ID
		List<Integer> allTitleId = ed.getAllExamTitleId(exampaper);
		ArrayList<Integer> allTitleIdArray = new ArrayList<Integer>(allTitleId);
		
		for(int index:allTitleIdArray){
			ExamTitle title = ed.getExamTitleById(index);
			newExam.addTitle(title);
			List<ExamOption> optionsList = ed.getExamOptionsOfTitle(title);
			newExam.addAllExamOptions(title, optionsList);
		}		
		return newExam;
	}
	
	
	//获取指定类型的试卷
	public String getExamPaper(){
		System.out.println("getExamPaper: "+paperName);
		ExamPaper exampaper = null;
		Exam exam = null;
		if(paperName!=null){
			ExamPaperDao dao = new ExamPaperDao();
			exampaper = dao.getExamPaperByCategory(paperName);
		}
		newestNewsList = Cache.getNewestNewsList(NewsPageInfo.NEWSPAGEINFO.getNewestNewsCount());//初始化每日推荐新闻列表
		pictureNewsList = Cache.getNewsList("图片新闻链接", 0,NewsPageInfo.NEWSPAGEINFO.getPictureNewsCount()-1 );//初始化焦点图片新闻
		hotestNewsList=Cache.getHotestNewsList();	
		if(exampaper!=null){
			//获取试卷
			System.out.println("exampaper is not null "+paperName);
			exam = getExamByPaperName(exampaper);				
			paperDescription=exampaper.getDescription();
			optionsOfTitle = new LinkedList<List<ExamOption>>();
			if(exam!=null){				
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				System.out.println("examKey::"+examKey);
				session.setAttribute(examKey, exam);
				listOftitle = exam.getAllExamTitle();
				HashMap<ExamTitle, List<ExamOption>> map = exam.getTotalExam();
				for(ExamTitle title:listOftitle){					
					optionsOfTitle.add(map.get(title));
				}
				return SUCCESS;
			}
		}
		return ERROR;
	}
	
	//获取所有试卷类型，对应于choosePaperAction
	public String getAllExamPaper(){
		System.out.println("getAllExamPaper: ");
		ExamPaperDao dao = new ExamPaperDao();
		examPaperList = dao.getAllExamPaper();
		newestNewsList = Cache.getNewestNewsList(NewsPageInfo.NEWSPAGEINFO.getNewestNewsCount());//初始化每日推荐新闻列表
		pictureNewsList = Cache.getNewsList("图片新闻链接", 0,NewsPageInfo.NEWSPAGEINFO.getPictureNewsCount()-1 );//初始化焦点图片新闻
		hotestNewsList=Cache.getHotestNewsList();	
		
		return SUCCESS;
	}
	
	//评判试卷
	public String judgingPaper(){
		int sum = 0;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Exam exam = (Exam) session.getAttribute(examKey);
		HashSet<Integer> set = new HashSet<Integer>();//用于保存所有选中的选项的ID
		set.addAll(checkedOptionList);
		HashMap<ExamTitle,List<ExamOption>> map = exam.getTotalExam();
		System.out.println("长度++++++："+map.size());
		Set<ExamTitle> keySet =  map.keySet(); 
		for(ExamTitle tem:keySet){
			List<ExamOption> list = map.get(tem);
			for(ExamOption option:list){
				
				//如果该选项被选中则加上该选项的权值
				if(set.contains(option.getEmOpId())){
					sum+=option.getEmOptionWeight();
				}
			}
		}
		
		List<ExamEvalution> examEvalutionList = exam.getAllExamEvalution();
		for(ExamEvalution ee:examEvalutionList){
			if(ee.getLowScore()<=sum && sum<=ee.getHighScore()){
				this.evaluate = ee.getDescription();
				System.out.println(this.evaluate);
				break;
			}
		}
		return SUCCESS;
	}


	public String getPaperName() {
		return paperName;
	}


	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}


	public int getCountOftitle() {
		return countOftitle;
	}


	public void setCountOftitle(int countOftitle) {
		this.countOftitle = countOftitle;
	}


	public List<ExamTitle> getListOftitle() {
		return listOftitle;
	}


	public void setListOftitle(List<ExamTitle> listOftitle) {
		this.listOftitle = listOftitle;
	}


	public List<List<ExamOption>> getOptionsOfTitle() {
		return optionsOfTitle;
	}


	public void setOptionsOfTitle(List<List<ExamOption>> optionsOfTitle) {
		this.optionsOfTitle = optionsOfTitle;
	}


	public List<Integer> getCheckedOptionList() {
		return checkedOptionList;
	}


	public void setCheckedOptionList(List<Integer> checkedOptionList) {
		this.checkedOptionList = checkedOptionList;
	}


	public List<ExamPaper> getExamPaperList() {
		return examPaperList;
	}


	public void setExamPaperList(List<ExamPaper> examPaperList) {
		this.examPaperList = examPaperList;
	}


	public String getEvaluate() {
		return evaluate;
	}


	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}


	public String getPaperDescription() {
		return paperDescription;
	}


	public void setPaperDescription(String paperDescription) {
		this.paperDescription = paperDescription;
	}


	public List<News> getNewestNewsList() {
		return newestNewsList;
	}


	public void setNewestNewsList(List<News> newestNewsList) {
		this.newestNewsList = newestNewsList;
	}


	public List<News> getPictureNewsList() {
		return pictureNewsList;
	}


	public void setPictureNewsList(List<News> pictureNewsList) {
		this.pictureNewsList = pictureNewsList;
	}


	public List<News> getHotestNewsList() {
		return hotestNewsList;
	}


	public void setHotestNewsList(List<News> hotestNewsList) {
		this.hotestNewsList = hotestNewsList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getExamKey() {
		return examKey;
	}
	
	
	
	
	
	
	
}
