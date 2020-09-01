const showToast = (status, message) => {
    let toastType;

    switch (status) {
        case "success":
            toastType = "success";
            break;

        case "warning":
            toastType = "warning";
            break;

        case "error":
            toastType = "danger";
            break;

        default:
            toastType = "dark";
            break;
    }


    $(document).Toasts('create', {
        class: `bg-${toastType} custom-toast`,
        title: status[0].toUpperCase() + status.substring(1),   //  Capitalize first character
        body: message,
        position: 'bottomLeft',
        autohide: true,
        delay: 4000
    });
};
