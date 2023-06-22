package com.example.demo.controller;

import com.example.demo.config.GlobalMessageSource;
import com.example.demo.exception.QuestionNotFoundException;
import com.example.demo.model.FAQ;
import com.example.demo.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.demo.exception.ErrorConstants.QUESTION_NOT_FOUND;


@RestController
@CrossOrigin(origins = "*", allowedHeaders="*", maxAge=3600)
@RequestMapping("/faq")
public class FAQAPIService {
	
	@Autowired
	FAQRepository faqRepo;
	@Autowired
	GlobalMessageSource globalMessageSource;
	
	@PostMapping("/addFaq")
	public FAQ Add(@RequestBody FAQ faq)
	{
		if(faq.getQuestion().isBlank())
			throw new QuestionNotFoundException(QUESTION_NOT_FOUND,globalMessageSource.get(QUESTION_NOT_FOUND));
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
	public FAQ updateFaq(@RequestBody FAQ faq, @PathVariable String id)
	{
		Optional<FAQ> faqDb = faqRepo.findById(id);
		if(faqDb.isPresent())
		{
			FAQ faqUpdate = faqDb.get();
			faqUpdate.setQuestion(faq.getQuestion());
			faqUpdate.setDescription(faq.getDescription());
			faqRepo.save(faqUpdate);
			return faqUpdate;
		}
		else 
		{
			// Create a new FAQ entry with the provided ID
			faq.setId(id);
			faqRepo.save(faq);
			return faq;
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
