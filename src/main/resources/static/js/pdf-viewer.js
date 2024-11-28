document.addEventListener('DOMContentLoaded', function () {
    const containers = document.querySelectorAll('.pdf-container'); // Все контейнеры PDF

    const pdfjsLib = window['pdfjs-dist/build/pdf'];
    pdfjsLib.GlobalWorkerOptions.workerSrc = 'https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.worker.min.js';

    containers.forEach(container => {
        const url = container.dataset.url; // Получаем путь к PDF
        const index = container.dataset.index; // Уникальный индекс

        const controls = document.querySelector(`#controls-${index}`);
        const prevButton = document.getElementById(`prev-button-${index}`);
        const nextButton = document.getElementById(`next-button-${index}`);
        const pageInfo = document.getElementById(`page-info-${index}`);

        let pdf = null;
        let currentPage = 1;
        let totalPages = 0;

        // Функция для рендеринга страницы
        function renderPage(pageNum) {
            pdf.getPage(pageNum).then(page => {
                const viewport = page.getViewport({ scale: 1 }); // Масштаб страницы
                const containerWidth = container.clientWidth; // Ширина контейнера
                const scale = containerWidth / viewport.width;
                const scaledViewport = page.getViewport({ scale });

                // Настройка canvas
                const canvas = document.createElement('canvas');
                const context = canvas.getContext('2d');
                canvas.height = scaledViewport.height;
                canvas.width = scaledViewport.width;

                // Настройка высоты контейнера
                container.style.height = `${scaledViewport.height}px`;

                // Рендер страницы
                const renderContext = {
                    canvasContext: context,
                    viewport: scaledViewport
                };
                page.render(renderContext);

                // Очистка контейнера и добавление canvas
                container.innerHTML = '';
                container.appendChild(canvas);
            }).catch(err => {
                console.error(`Ошибка при рендеринге страницы ${pageNum}:`, err);
            });
        }

        // Функция для обновления кнопок и информации
        function updateControls() {
            if (totalPages === 1) {
                controls.style.display = 'none'; // Скрыть навигацию, если одна страница
            } else {
                prevButton.disabled = currentPage === 1;
                nextButton.disabled = currentPage === totalPages;
                pageInfo.textContent = `Страница ${currentPage} из ${totalPages}`;
            }
        }

        // Загрузка PDF
        pdfjsLib.getDocument(url).promise.then(pdfDoc => {
            pdf = pdfDoc;
            totalPages = pdf.numPages;

            // Рендер первой страницы
            renderPage(currentPage);
            updateControls();
        }).catch(err => {
            console.error('Ошибка при загрузке PDF:', err);
        });

        // События для кнопок
        prevButton.addEventListener('click', () => {
            if (currentPage > 1) {
                currentPage -= 1;
                renderPage(currentPage);
                updateControls();
            }
        });

        nextButton.addEventListener('click', () => {
            if (currentPage < totalPages) {
                currentPage += 1;
                renderPage(currentPage);
                updateControls();
            }
        });
    });
});
