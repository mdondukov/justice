// file-upload.js

// Загрузка миниатюры
function uploadThumbnail() {
    const fileInput = document.getElementById('thumb-file');
    const previewContainer = document.getElementById('thumb-preview');

    if (fileInput.files.length > 0) {
        const formData = new FormData();
        formData.append('thumbFile', fileInput.files[0]);

        fetch('/admin/thumb/upload', {
            method: 'POST',
            body: formData, // FormData автоматически добавляет нужный заголовок multipart/form-data
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to upload thumbnail');
                }
                return response.text(); // Ожидаем HTML-ответ от сервера
            })
            .then(data => {
                // Обновляем блок для предпросмотра
                previewContainer.innerHTML = data;
            })
            .catch(error => {
                console.error('Error uploading thumbnail:', error);
                previewContainer.innerHTML = `<p class="text-red-500">Failed to upload thumbnail</p>`;
            });
    }
}

// Загрузка изображений галереи
function uploadGalleryImages() {
    const fileInput = document.getElementById('gallery-files');
    const previewContainer = document.getElementById('gallery-preview');

    if (fileInput.files.length > 0) {
        const formData = new FormData();
        for (let i = 0; i < fileInput.files.length; i++) {
            formData.append('galleryFiles', fileInput.files[i]);
        }

        fetch('/admin/gallery/upload', {
            method: 'POST',
            body: formData, // FormData автоматически добавляет нужный заголовок multipart/form-data
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to upload gallery images');
                }
                return response.text(); // Ожидаем HTML-ответ от сервера
            })
            .then(data => {
                // Обновляем блок для предпросмотра галереи
                previewContainer.innerHTML = data;
            })
            .catch(error => {
                console.error('Error uploading gallery images:', error);
                previewContainer.innerHTML = `<p class="text-red-500">Failed to upload gallery images</p>`;
            });
    }
}
