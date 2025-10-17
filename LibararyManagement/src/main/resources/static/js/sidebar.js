// Sidebar Mobile Toggle Functionality
document.addEventListener('DOMContentLoaded', function () {
    const sidebar = document.querySelector('.sidebar');
    const sidebarToggle = document.querySelector('.sidebar-toggle');
    const sidebarOverlay = document.querySelector('.sidebar-overlay');

    // Create toggle button if it doesn't exist
    if (!sidebarToggle) {
        const toggleBtn = document.createElement('button');
        toggleBtn.className = 'sidebar-toggle';
        toggleBtn.innerHTML = '<i class="fas fa-bars"></i>';
        toggleBtn.setAttribute('aria-label', 'Toggle Sidebar');
        document.body.appendChild(toggleBtn);
    }

    // Create overlay if it doesn't exist
    if (!sidebarOverlay) {
        const overlay = document.createElement('div');
        overlay.className = 'sidebar-overlay';
        document.body.appendChild(overlay);
    }

    // Get references to the created elements
    const toggle = document.querySelector('.sidebar-toggle');
    const overlay = document.querySelector('.sidebar-overlay');

    // Toggle sidebar function
    function toggleSidebar() {
        sidebar.classList.toggle('show');
        overlay.classList.toggle('show');

        // Update aria-expanded attribute
        const isExpanded = sidebar.classList.contains('show');
        toggle.setAttribute('aria-expanded', isExpanded);

        // Prevent body scroll when sidebar is open
        if (isExpanded) {
            document.body.style.overflow = 'hidden';
        } else {
            document.body.style.overflow = '';
        }
    }

    // Close sidebar function
    function closeSidebar() {
        sidebar.classList.remove('show');
        overlay.classList.remove('show');
        toggle.setAttribute('aria-expanded', 'false');
        document.body.style.overflow = '';
    }

    // Event listeners
    if (toggle) {
        toggle.addEventListener('click', toggleSidebar);
    }

    if (overlay) {
        overlay.addEventListener('click', closeSidebar);
    }

    // Close sidebar when clicking on nav links (mobile)
    const navLinks = document.querySelectorAll('.sidebar .nav-link');
    navLinks.forEach(link => {
        link.addEventListener('click', function () {
            if (window.innerWidth <= 768) {
                closeSidebar();
            }
        });
    });

    // Handle window resize
    window.addEventListener('resize', function () {
        if (window.innerWidth > 768) {
            closeSidebar();
        }
    });

    // Handle escape key
    document.addEventListener('keydown', function (e) {
        if (e.key === 'Escape' && sidebar.classList.contains('show')) {
            closeSidebar();
        }
    });

    // Set active nav link based on current URL
    function setActiveNavLink() {
        const currentPath = window.location.pathname;
        const navLinks = document.querySelectorAll('.sidebar .nav-link');

        navLinks.forEach(link => {
            link.classList.remove('active');
            const href = link.getAttribute('href');

            if (href && href !== '#') {
                // Exact match for dashboard
                if (href === '/dashboard' && currentPath === '/dashboard') {
                    link.classList.add('active');
                }
                // Exact match for overdue-records
                else if (href === '/overdue-records' && currentPath === '/overdue-records') {
                    link.classList.add('active');
                }
                // Exact match for memberships
                else if (href === '/memberships' && currentPath === '/memberships') {
                    link.classList.add('active');
                }
                // Starts with match for books and categories
                else if ((href === '/books/list' && currentPath.startsWith('/books')) ||
                    (href === '/categories/create' && currentPath.startsWith('/categories'))) {
                    link.classList.add('active');
                }
            }
        });
    }

    // Set active nav link on page load
    setActiveNavLink();
});

// Smooth scroll for sidebar navigation
document.addEventListener('DOMContentLoaded', function () {
    const navLinks = document.querySelectorAll('.sidebar .nav-link[href^="#"]');

    navLinks.forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();
            const targetId = this.getAttribute('href').substring(1);
            const targetElement = document.getElementById(targetId);

            if (targetElement) {
                targetElement.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
});