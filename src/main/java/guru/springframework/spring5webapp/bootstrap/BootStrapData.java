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

        System.out.println("Started in bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("Noolu Publishers");
        publisher.setCity("Toronto");
        publisher.setState("Ontario");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author mohan = new Author("Mohan", "Noolu");
        Book ddd = new Book("Domain Driven Design", "12345");
        mohan.getBooks().add(ddd);
        ddd.getAuthors().add(mohan);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);


        authorRepository.save(mohan);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author pavan = new Author("Pavan", "Noolu");
        Book cc = new Book("Clean Code", "54321");
        pavan.getBooks().add(cc);
        cc.getAuthors().add(pavan);
        cc.setPublisher(publisher);
        publisher.getBooks().add(cc);

        authorRepository.save(pavan);
        bookRepository.save(cc);
        publisherRepository.save(publisher);


        System.out.println("No. of Books: " + bookRepository.count());
        System.out.println("Publisher No. of Books: " + publisher.getBooks().size());
    }
}
