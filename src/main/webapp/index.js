window.onload = () => {
  fetchPosts();
};

function fetchPosts() {
  const API_URL = "http://localhost:8080/api";
  const POSTS_API_URL = `${API_URL}/posts`;

  fetch(POSTS_API_URL)
    .then((response) => response.json())
    .then(createPosts);
}

function createPosts(posts) {
  const postsList = document.getElementById("posts-list");

  posts.forEach((post) => {
    const image = document.createElement("img");
    image.setAttribute("src", post.url);

    const postImageDiv = document.createElement("div");
    postImageDiv.classList.add("post-image");
    postImageDiv.append(image);

    const goToPostIcon = document.createElement("i");
    goToPostIcon.classList.add("fa", "fa-arrow-right");

    const goToPostLink = document.createElement("a");
    goToPostLink.setAttribute("href", "#");
    goToPostLink.append(goToPostIcon);

    const postFooterDiv = document.createElement("div");
    postFooterDiv.classList.add("post-footer");
    postFooterDiv.append(goToPostLink);

    const postDiv = document.createElement("div");
    postDiv.classList.add("post");
    postDiv.append(postImageDiv);
    postDiv.append(postFooterDiv);

    postsList.append(postDiv);
  });
}
