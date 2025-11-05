import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isBorrowed() { return isBorrowed; }
    public void setBorrowed(boolean borrowed) { this.isBorrowed = borrowed; }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", borrowed=" + isBorrowed +
                '}';
    }
}

class Member {
    private String name;
    private String memberId;
    private List<Book> borrowedBooks;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters
    public String getName() { return name; }
    public String getMemberId() { return memberId; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setBorrowed(true);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setBorrowed(false);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", memberId='" + memberId + '\'' +
                ", borrowedBooks=" + borrowedBooks.size() +
                '}';
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    // Book operations
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Book removed with ISBN: " + isbn);
    }

    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void displayBooks() {
        System.out.println("Books in library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Member operations
    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added: " + member.getName());
    }

    public void removeMember(String memberId) {
        members.removeIf(member -> member.getMemberId().equals(memberId));
        System.out.println("Member removed with ID: " + memberId);
    }

    public Member findMember(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    public void displayMembers() {
        System.out.println("Members:");
        for (Member member : members) {
            System.out.println(member);
        }
    }

    // Borrowing operations
    public void borrowBook(String memberId, String bookTitle) {
        Member member = findMember(memberId);
        Book book = searchBook(bookTitle);
        if (member != null && book != null && !book.isBorrowed()) {
            member.borrowBook(book);
            System.out.println(member.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Borrow failed: Member or book not found, or book already borrowed.");
        }
    }

    public void returnBook(String memberId, String bookTitle) {
        Member member = findMember(memberId);
        Book book = searchBook(bookTitle);
        if (member != null && book != null && book.isBorrowed()) {
            member.returnBook(book);
            System.out.println(member.getName() + " returned " + book.getTitle());
        } else {
            System.out.println("Return failed: Member or book not found, or book not borrowed.");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Display Books");
            System.out.println("5. Add Member");
            System.out.println("6. Remove Member");
            System.out.println("7. Display Members");
            System.out.println("8. Borrow Book");
            System.out.println("9. Return Book");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;
                case 2:
                    System.out.print("Enter ISBN to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    break;
                case 3:
                    System.out.print("Enter book title to search: ");
                    String searchTitle = scanner.nextLine();
                    Book foundBook = library.searchBook(searchTitle);
                    if (foundBook != null) {
                        System.out.println("Found: " + foundBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    library.displayBooks();
                    break;
                case 5:
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    String id = scanner.nextLine();
                    library.addMember(new Member(name, id));
                    break;
                case 6:
                    System.out.print("Enter member ID to remove: ");
                    String removeId = scanner.nextLine();
                    library.removeMember(removeId);
                    break;
                case 7:
                    library.displayMembers();
                    break;
                case 8:
                    System.out.print("Enter member ID: ");
                    String borrowId = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowId, borrowTitle);
                    break;
                case 9:
                    System.out.print("Enter member ID: ");
                    String returnId = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnId, returnTitle);
                    break;
                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}