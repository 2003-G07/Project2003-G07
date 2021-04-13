function validateInput(inputfield, errorid) {
    let errorElement = document.getElementById(errorid);
    inputfield.setCustomValidity('');
    inputfield.setAttribute("style", "border: 2px solid #00AA00");
    errorElement.setAttribute('hidden',true);
    inputfield.checkValidity();
    //console.log(inputfield.validity);
}

function invalidInput(inputfield, errorLabelId, errorTextMessage) {
    let errorLabel = document.getElementById(errorLabelId);
    inputfield.setCustomValidity(' ');
    if(inputfield.validity.valueMissing) {
        errorLabel.innerText = inputfield.getAttribute('name') + " måste fyllas i." ;
    } else {
        errorLabel.innerText = errorTextMessage;
    }
    errorLabel.removeAttribute('hidden');
    inputfield.setAttribute("style", "border: 2px solid #FF0000");
}

