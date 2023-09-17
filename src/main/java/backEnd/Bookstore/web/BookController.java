package backEnd.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import backEnd.Bookstore.domain.Book;
import backEnd.Bookstore.domain.BookRepository;
import backEnd.Bookstore.domain.CategoryRepository;
import jakarta.validation.Valid;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	@Autowired 
	private CategoryRepository catrepository;
	
	@GetMapping("/*")
	public String goTo() {
		System.out.println("redirecting to booklist");
		return("redirect:/booklist");
	}
	
	@GetMapping("/index")
	public String getStarted() {
		return("index");
	}

	@GetMapping("/booklist")
	public String listBooks(Model model) {
		System.out.println("loading booklist");
		model.addAttribute("books", repository.findAll());
		return("booklist");
	}
	
	@GetMapping("/delete/{id}") 	// Muuta tämä DeleteMapping metodiksi
	public String deleteBook(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return("redirect:/booklist");
	}
	
	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", catrepository.findAll());
		return("addbook");
	}
	
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book",repository.findById(id));
		model.addAttribute("categories", catrepository.findAll());
		return("editbook");
	}
	
	@PostMapping("/saveNew")
	public String saveNewBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("error in /saveNew");
			model.addAttribute("categories", catrepository.findAll());
			return("addbook");
		}
		repository.save(book);
		return("redirect:/booklist");
	}

	@PostMapping("/saveEdit")
	public String saveEditedBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("error in /saveEdit");
			model.addAttribute("categories", catrepository.findAll());
			return("editbook");
		}
		repository.save(book);
		return("redirect:/booklist");
	}
}
