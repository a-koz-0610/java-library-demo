import crud from "./crud/crud";
import Header from "./components/Header";
import Books from "./components/Books";
import Book from "./components/Book";
import Footer from "./components/Footer";
import Home from "./components/Home";
import Contact from "./components/Contact";
import HashTags from "./components/HashTags";
import '../css/style.css';
import '../css/layout.css';


buildPage();

function buildPage() {
    header();
    navBooks();
    navHashTags();
    home();
    contact();
    footer();
}

function header() {
    const headerElem = document.querySelector('.header');
    headerElem.innerHTML = Header();
}

function home() {
    const homeElem = document.querySelector('.nav-list__home');
    homeElem.addEventListener('click', () => {
        const app = document.querySelector('#app');
        app.innerHTML = Home();
    })
}

function contact() {
    const contactElem = document.querySelector('.nav-list__contact');
    contactElem.addEventListener('click', () => {
        const app = document.querySelector('#app');
        app.innerHTML = Contact();
    });
}

function navBooks() {
    const booksLi = document.querySelector('.nav-list__books');
    booksLi.addEventListener('click', () => {
        const app = document.querySelector('#app');
        crud.getRequest('http://localhost:8080/rest-books', books => {
            app.innerHTML = Books(books);
        })
        renderBookInfo();
    })
}

function navHashTags() {
    const hashTagsLi = document.querySelector('.nav-list__hashtags');
    hashTagsLi.addEventListener('click', () => {
        const app = document.querySelector('#app');
        crud.getRequest('http://localhost:8080/rest-hashtags', hashTags => {
            app.innerHTML = HashTags(hashTags);
        })
    })

    app.addEventListener('click', () => {
        if (event.target.classList.contains('add-hashtag__submit')) {
            const hashTagName = event.target.parentElement.querySelector('.add-hashtag__name').value;
            console.log(hashTagName);
            crud.postRequest('http://localhost:8080/rest-hashtags/add', {
                hashTagName: hashTagName
            }, (hashTags) => app.innerHTML = HashTags(hashTags));
        }
    })

}

function renderBookInfo() {
    // const app = document.querySelector('#app');
    app.addEventListener('click', () => {
        if (event.target.classList.contains('books-list__title')) {
            const bookId = event.target.querySelector('#bookId').value;
            crud.getRequest(`http://localhost:8080/rest-books/${bookId}`, book => {
                app.innerHTML = Book(book);
            })
        }
    })

    app.addEventListener('click', () => {
        if (event.target.classList.contains('add-hashtag__bookSubmit')) {
            const bookId = event.target.parentElement.querySelector('#bookId').value;
            const hashTagName = event.target.parentElement.querySelector('.add-hashtag__name').value;
            console.log('bookID', bookId);
            console.log('name', hashTagName);
            crud.postRequest(`http://localhost:8080/rest-books/${bookId}/add-hashtag`, {
                hashTagName: hashTagName
            }, (book) => app.innerHTML = Book(book));
        }
    })
}

function footer() {
    const footerElem = document.querySelector('.footer');
    footerElem.innerHTML = Footer();
}
