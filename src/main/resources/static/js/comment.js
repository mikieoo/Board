// 댓글 삭제 기능
document.querySelectorAll('.delete-comment-btn').forEach(button => {
    button.addEventListener('click', event => {
        const commentId = event.target.dataset.commentId;
        const boardId = document.getElementById('board-id').value;

        fetch(`/api/comments/${commentId}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('댓글이 삭제되었습니다.');
                location.replace(`/boards/${boardId}`);
            });
    });
});

// 댓글 수정 기능
document.querySelectorAll('.modify-comment-btn').forEach(button => {
    button.addEventListener('click', event => {
        const commentId = event.target.dataset.commentId;
        const boardId = document.getElementById('board-id').value;
        const content = document.getElementById(`comment-content-${commentId}`).value;

        fetch(`/api/comments/${commentId}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                content: content
            })
        })
            .then(() => {
                alert('댓글이 수정되었습니다.');
                location.replace(`/boards/${boardId}`);
            });
    });
});
