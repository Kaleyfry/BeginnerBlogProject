package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogPostController {
	
	private BlogPost blogPost;
	
	@Autowired
	private BlogPostRepository blogPostRepository;
//	private static List<BlogPost> posts = new ArrayList<>();
	
	@GetMapping(value="/")
	public String index(BlogPost blogPost, Model model) {
//		model.addAttribute("posts",posts);
		model.addAttribute("repo", blogPostRepository.findAll());
		return "blogpost/index";
	    }
	
	@GetMapping(value="/blog_posts/new")
		public String newBlog (BlogPost blogpost) {
		return "blogpost/new";
	}

	@GetMapping(value="/blog_posts/edit/{id}")
		public String editBlog (@PathVariable Long id, Model model) {
		Optional<BlogPost> result = blogPostRepository.findById(id);
		BlogPost blogPost = null;
		
		if(result.isPresent()) {
			blogPost = result.get();
		} else {
				throw new RuntimeException("Did not find post id" + id);
		}
		
		model.addAttribute("blogPost", blogPost);
		return "blogpost/new";
	}
	
	@GetMapping("/blog_posts/delete/{id}")
	public String deletePostById(@PathVariable Long id, BlogPost blogpost) {
		blogPostRepository.deleteById(id);
		return "redirect:/";
	}
	
	 @PostMapping(value = "/blog_posts/new")
	    public String assNewBlogPost(BlogPost blogPost, Model model) {
		blogPostRepository.save(blogPost);
//		posts.add(blogPost);
		model.addAttribute("title", blogPost.getTitle());
		model.addAttribute("author", blogPost.getAuthor());
		model.addAttribute("blogEntry", blogPost.getBlogEntry());
		return "blogpost/results";
	    }
	 
	 
	 
//	    @RequestMapping(value = "/blog_posts/{id}", method = RequestMethod.DELETE)
//	    public String deletePostWithId(@PathVariable Long id,
//	    								BlogPost blogPost) {
//	        blogPostRepository.deleteById(id);
//	        return "/";
//
//	    }

}
