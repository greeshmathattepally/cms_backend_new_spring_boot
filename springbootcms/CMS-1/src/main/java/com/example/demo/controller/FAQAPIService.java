package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.LifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Add;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FAQ;
import com.example.demo.model.HelpArticle;
import com.example.demo.repository.FAQRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders="*", maxAge=3600)
@RequestMapping("/faq")
public class FAQAPIService {
	
	@Autowired
	FAQRepository faqRepo;
	
	
	@PostMapping("/addFaq")
	public FAQ Add(@RequestBody FAQ faq)
	{
		faqRepo.save(faq);
		return faq;
	}
	
	@GetMapping("/allFaqs")
	public List<FAQ> getAllFaqs()
	{
		return faqRepo.findAll();
	}
	
	@GetMapping("/get/{id}")
	public FAQ getFaqById(@PathVariable String id)
	{
		Optional<FAQ> faqDb = faqRepo.findById(id);
		return faqDb.get();
	}
	
	@DeleteMapping("/delete/{id}")
	public FAQ deleteFaq(@PathVariable String id)
	{
		if(faqRepo.existsById(id))
		{
			Optional<FAQ> faqDb = faqRepo.findById(id);
			
			faqRepo.deleteById(id);
			return faqDb.get();
		}
		else 
			return null;
	}
	
	@PutMapping("/update/{id}")
	public String updateFaq(@RequestBody FAQ faq, @PathVariable String id)
	{
		Optional<FAQ> faqDb = faqRepo.findById(id);
		if(faqDb.isPresent())
		{
			FAQ faqUpdate = faqDb.get();
			faqUpdate.setId(faq.getId());
			faqUpdate.setQuestion(faq.getQuestion());
			faqUpdate.setDescription(faq.getDescription());
			faqRepo.save(faqUpdate);
			return "Faq with id "+id+" updated";
		}
		else 
		{
			return "Faq with id "+id+" does not exist";
		}
	}
	/*
	@PostMapping("/addHelpArticle")
	public String Add(@RequestBody HelpArticle helpArticle)
	{
		helpArticleRepo.save(helpArticle);
		return "help Article added"+helpArticle.getImageWithMetaDataImage();
	}
	
	@GetMapping("/allHelpArticles")
	public List<HelpArticle> getAllHelpArticles()
	{
		return helpArticleRepo.findAll();
	}
	
	@GetMapping("/get/{id}")
	public HelpArticle getById(@PathVariable String id)
	{
		Optional<HelpArticle> helpArticleDb = helpArticleRepo.findById(id);
		return helpArticleDb.get();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteHelpArticle(@PathVariable String id)
	{
		if(helpArticleRepo.existsById(id))
		{
			helpArticleRepo.deleteById(id);
			return "HelpArticle with id "+id+" deleted";
		}
		else 
			return "HelpArticle with id "+id+" does not exist";
	}
	
	@PutMapping("/update/{id}")
	public String updateHelpArticle(@RequestBody HelpArticle helpArticle, @PathVariable String id)
	{
		Optional<HelpArticle> helpArticleDb = helpArticleRepo.findById(id);
		if(helpArticleDb.isPresent())
		{
			
			HelpArticle helpArticleUpdate = helpArticleDb.get();
			
			helpArticleUpdate.setId(helpArticle.getId());
			helpArticleUpdate.setTitle(helpArticle.getTitle());
			helpArticle.setSubtitle(helpArticle.getSubtitle());
			
			HelpArticle.Image helpArticleUpdateImage = helpArticle.getImageWithMetaDataImage();
			helpArticleUpdateImage.setImageBinary(helpArticle.getImageWithMetaDataImage().getImageBinary());
			helpArticleUpdateImage.setAltText(helpArticle.getImageWithMetaDataImage().getAltText());
			helpArticleUpdateImage.setImageURL(helpArticle.getImageWithMetaDataImage().getImageURL());
			helpArticle.setImageWithMetaDataImage(helpArticleUpdateImage);
			
			helpArticle.setParagraph(helpArticle.getParagraph());
			
			helpArticleRepo.save(helpArticleUpdate);
			return "helpArticle with id "+id+" updated";
		}
		else 
		{
			return "helpArticle with id "+id+" does not exist";
		}
	}
	*/
}
