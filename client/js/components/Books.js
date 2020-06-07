export default function Books(books) {
    return `
    <h1>Books</h1>
    <ul class="books-list">
    ${books.map(book => {
        return `
        <li class='books-list__title'>${book.title}
            <input type='hidden' id='bookId' value='${book.id}'>
        </li>
        `
    }).join('')}
    </ul>
    `
}