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

document.addEventListener("DOMContentLoaded", function () {
    // 댓글 수정 기능
    document.querySelectorAll('.comment-modify-btn').forEach(button => {
        button.addEventListener('click', event => {
            const commentElement = event.target.closest('.comment');
            const boardId = document.getElementById('board-id').value;
            const commentId = commentElement.querySelector('.comment-id').value;
            const commentTextElement = commentElement.querySelector('p');
            const originalComment = commentTextElement.textContent;

            // 기존 <p> 태그를 <textarea>로 변환
            const textarea = document.createElement('textarea');
            textarea.className = 'form-control';
            textarea.style.marginBottom = '10px'; // 아래 간격 추가
            textarea.value = originalComment;

            // 버튼들을 담을 div 생성
            const buttonContainer = document.createElement('div');
            buttonContainer.style.display = 'flex';
            buttonContainer.style.gap = '10px'; // 버튼 사이의 간격 추가

            // 수정 및 취소 버튼 추가
            const saveButton = document.createElement('button');
            saveButton.textContent = '저장';
            saveButton.className = 'btn btn-sm btn-primary';

            const cancelButton = document.createElement('button');
            cancelButton.textContent = '취소';
            cancelButton.className = 'btn btn-sm btn-secondary';

            // 버튼들을 container에 추가
            buttonContainer.appendChild(saveButton);
            buttonContainer.appendChild(cancelButton);

            // 기존 <p> 태그 숨기고 수정 박스 표시
            commentTextElement.style.display = 'none';
            commentElement.querySelector('.comment-modify-btn').style.display = 'none';
            commentElement.querySelector('.comment-delete-btn').style.display = 'none';
            commentElement.appendChild(textarea);
            commentElement.appendChild(buttonContainer);

            // 저장 버튼 클릭 이벤트
            saveButton.addEventListener('click', () => {
                const newComment = textarea.value;
                fetch(`/api/boards/${boardId}/comments/${commentId}`, {
                    method: 'PUT',
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        comment: newComment
                    })
                })
                    .then(() => {
                        alert('수정이 완료되었습니다.');
                        location.replace(`/boards/${boardId}`);
                    })
                    .catch(error => {
                        console.error('댓글 수정 중 오류 발생:', error);
                        alert('댓글 수정 중 오류가 발생했습니다.');
                    });
            });

            // 취소 버튼 클릭 이벤트
            cancelButton.addEventListener('click', () => {
                textarea.remove();
                buttonContainer.remove();
                commentTextElement.style.display = 'block';
                commentElement.querySelector('.comment-modify-btn').style.display = 'inline-block';
                commentElement.querySelector('.comment-delete-btn').style.display = 'inline-block';
            });
        });
    });

    // 댓글 삭제 기능
    document.querySelectorAll('.comment-delete-btn').forEach(button => {
        button.addEventListener('click', event => {
            const commentElement = event.target.closest('.comment');
            const boardId = document.getElementById('board-id').value;
            const commentId = commentElement.querySelector('.comment-id').value;

            if (confirm("정말 삭제하시겠습니까?")) {
                fetch(`/api/boards/${boardId}/comments/${commentId}`, {
                    method: 'DELETE'
                })
                    .then(() => {
                        alert('삭제가 완료되었습니다.');
                        location.replace(`/boards/${boardId}`);
                    })
                    .catch(error => {
                        console.error('댓글 삭제 중 오류 발생:', error);
                        alert('댓글 삭제 중 오류가 발생했습니다.');
                    });
            }
        });
    });
});
