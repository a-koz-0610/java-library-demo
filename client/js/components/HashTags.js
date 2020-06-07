export default function HashTags(hashTags) {
    return `
    <h1>Saved HashTags</h1>
    <ul class='hashTags-list'>
    ${hashTags.map(hashTag => {
        return `
        <li class='hashTags-list__name'>${hashTag.name}</li>
        `
    }).join('')}
    </ul>

    <section class='add-hashtag'>
        <input class='add-hashtag__name' type='text' placeholder='Add a Hashtag to Save' />
        <button class='add-hashtag__submit'>Save</button>
    </section>
    `
}