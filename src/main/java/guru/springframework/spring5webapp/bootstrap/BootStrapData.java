package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("started in bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG publishing");
        publisher.setCity("st petersburg");
        publisher.setState("fl");

        publisherRepository.save(publisher);

        System.out.println("publisher count : " + publisherRepository.count());

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("rod","Fer");
        Book java = new Book("Java Principles","541236");

        rod.getBooks().add(java);
        java.getAuthors().add(rod);

        java.setPublisher(publisher);
        publisher.getBooks().add(java);

        authorRepository.save(rod);
        bookRepository.save(java);
        publisherRepository.save(publisher);

        System.out.println("Book counts: " + bookRepository.count());
        System.out.println("publisher count: " + publisher.getBooks().size());

    }
}
