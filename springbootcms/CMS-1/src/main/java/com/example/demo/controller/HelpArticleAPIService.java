package com.example.demo.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FAQ;
import com.example.demo.model.HelpArticle;
import com.example.demo.model.HelpArticleWithImage;

import com.example.demo.repository.HelpArticleWithImageRepository;


@RestController
@RequestMapping("/helpArticle")
@CrossOrigin(origins = "*", allowedHeaders="*", maxAge=3600)
public class HelpArticleAPIService {


	@Autowired
	HelpArticleWithImageRepository helpArticleImageRepo;

	// @PostMapping("/addHelpArticle")
	// public String Add(@RequestBody HelpArticle helpArticle)
	// {
	// 	helpArticleRepo.save(helpArticle);
	// 	return "help Article added "+helpArticle.getParagraph();
	// }
	
	
	@PostMapping(path="/addHelpArticle2",
			consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public HelpArticleWithImage Add1(@RequestParam(required = false) String title, @RequestParam(required = false) String subtitle, @RequestPart(value="imageFile", required = false) MultipartFile imageFile, @RequestParam(required = false) String altText, @RequestParam(required = false) String imageURL, @RequestParam(required = false) String paragraph) throws IOException
	{
		HelpArticleWithImage helpArticleWithImage = helpArticleImageRepo.findTopByOrderByIdDesc();
		Long id2=(helpArticleWithImage!=null)?helpArticleWithImage.getId()+1 : 1L;
		HelpArticleWithImage helpArticleWithImageNew = new HelpArticleWithImage(id2, title, subtitle, altText, imageURL, paragraph);
		helpArticleWithImageNew.setImageFile(new Binary(BsonBinarySubType.BINARY, imageFile.getBytes()));
		helpArticleImageRepo.insert(helpArticleWithImageNew);  
		return helpArticleWithImageNew;
	}
	
	// @GetMapping(path="/allHelpArticles", produces = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	// public List<HelpArticle> getAllHelpArticles()
	// {
	// 	return helpArticleRepo.findAll();
	// }
	
	@GetMapping("/allHelpArticlesWithImages")
	public List<HelpArticleWithImage> getAllHelpArticlesWithImages()
	{
		return helpArticleImageRepo.findAll();
	}
	
	// @GetMapping("/get/{id}")
	// public HelpArticle getById(@PathVariable Long id)
	// {
	// 	Optional<HelpArticle> helpArticleDb = helpArticleRepo.findById(id);
	// 	return helpArticleDb.get();
	// }
	
	// @DeleteMapping("/delete/{id}")
	// public String deleteHelpArticle(@PathVariable Long id)
	// {
	// 	if(helpArticleRepo.existsById(id))
	// 	{
	// 		helpArticleRepo.deleteById(id);
	// 		return "HelpArticle with id "+id+" deleted";
	// 	}
	// 	else 
	// 		return "HelpArticle with id "+id+" does not exist";
	// }

	@DeleteMapping("/deleteHelpArticleWithImage/{id}")
	public HelpArticleWithImage deleteHelpArticleWithImage(@PathVariable Long id)
	{
		if(helpArticleImageRepo.existsById(id))
		{
			Optional<HelpArticleWithImage> helpArticleWithImDb = helpArticleImageRepo.findById(id);
			helpArticleImageRepo.deleteById(id);
			return helpArticleWithImDb.get();
		}
		else 
			return null;
	}

	
	@PutMapping("/update/{id}")
	public String updateHelpArticle(@RequestBody HelpArticle helpArticle, @PathVariable Long id)
	{
		Optional<HelpArticleWithImage> helpArticleDb = helpArticleImageRepo.findById(id);
		if(helpArticleDb.isPresent())
		{
			
			HelpArticleWithImage helpArticleUpdate = helpArticleDb.get();
			
			helpArticleUpdate.setId(helpArticle.getId());
			helpArticleUpdate.setTitle(helpArticle.getTitle());
			helpArticle.setSubtitle(helpArticle.getSubtitle());
			helpArticle.setParagraph(helpArticle.getParagraph());
			
			helpArticleImageRepo.save(helpArticleUpdate);
			return "helpArticle with id "+id+" updated";
		}
		else 
		{
			return "helpArticle with id "+id+" does not exist";
		}
	}
}
