// 게시물 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        const boardId = document.getElementById('board-id').value;

        fetch(`/api/boards/${boardId}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/boards/list');
            })
            .catch(error => {
                console.error('게시물 삭제 중 오류 발생:', error);
                alert('게시물 삭제 중 오류가 발생했습니다.');
            });
    });
}

// 게시물 수정 기능
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        const boardId = new URLSearchParams(location.search).get('id');

        fetch(`/api/boards/${boardId}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value,
                writer: document.getElementById('writer').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/boards/${boardId}`);
            })
            .catch(error => {
                console.error('게시물 수정 중 오류 발생:', error);
                alert('게시물 수정 중 오류가 발생했습니다.');
            });
    });
}

// 게시물 생성 기능
const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener('click', event => {
        fetch('/api/boards', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value,
                writer: document.getElementById('writer').value
            })
        })
            .then(() => {
                alert('등록 완료되었습니다.');
                location.replace('/boards/list');
            })
            .catch(error => {
                console.error('게시물 등록 중 오류 발생:', error);
                alert('게시물 등록 중 오류가 발생했습니다.');
            });
    });
}

// 댓글 생성 기능
const createCommentButton = document.getElementById('comment-create-btn');

if (createCommentButton) {
    createCommentButton.addEventListener('click', event => {
        const boardId = document.getElementById('board-id').value;

        fetch(`/api/boards/${boardId}/comments`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                boardId: boardId,
                comment: document.getElementById('comment-box').value
            })
        })
            .then(() => {
                alert('댓글이 등록되었습니다.');
                location.replace(`/boards/${boardId}`);
            });
    });
}

// 댓글 수정 기능
const modifyCommentButton = document.getElementById('comment-modify-btn');

if (modifyCommentButton) {
    modifyCommentButton.addEventListener('click', event => {
        const boardId = document.getElementById('board-id').value;
        const commentId = document.getElementById('comment-id').value;

        fetch(`/api/boards/${boardId}/comments/${commentId}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                comment: document.getElementById('comment-box').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/boards/${boardId}`);
            })
            .catch(error => {
                console.error('게시물 수정 중 오류 발생:', error);
                alert('게시물 수정 중 오류가 발생했습니다.');
            });
    });
}

// 댓글 삭제 기능
const deleteCommentButton = document.getElementById('comment-delete-btn');

if (deleteCommentButton) {
    deleteCommentButton.addEventListener('click', event => {
        const boardId = document.getElementById('board-id').value;
        const commentId = document.getElementById('comment-id').value;

        fetch(`/api/boards/${boardId}/comments/${commentId}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/boards/${boardId}');
            })
            .catch(error => {
                console.error('게시물 삭제 중 오류 발생:', error);
                alert('게시물 삭제 중 오류가 발생했습니다.');
            });
    });
}