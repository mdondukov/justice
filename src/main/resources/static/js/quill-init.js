document.addEventListener('DOMContentLoaded', function () {
    const quill = new Quill('#editor-quill', {
        theme: 'snow',
        modules: {
            toolbar: '#toolbar'
        }
    });

    // Устанавливаем начальное значение в редактор
    const contentInput = document.querySelector('#content');
    if (contentInput) {
        quill.root.innerHTML = contentInput.value; // Устанавливаем текущий HTML-контент
    }

    // Сохраняем контент редактора в скрытое поле формы
    quill.on('text-change', function () {
        if (contentInput) {
            contentInput.value = quill.root.innerHTML;
        }
    });
});
