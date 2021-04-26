/**
 * @param {*} inputfield är input elementet som ska valideras
 * @param {*} errorLabelId är id på den error label som visar eventuella fel
 * 
 * Först hämtas det errorElement som errorLabelId pekar på
 * Sen sätts html5 funktionaliteten för CustomValidity till tom för att nollställa detta
 * Efter det görs en grön ram runt input fältet (sätts om till röd om det visar sig vara fel senare)
 * errorElementet görs hidden (visas igen om det visar sig vara fel senare)
 * Slutligen så triggar vi funktionaliteten i html5 som kontrollerar inputfältets innehåll 
 */
function validateInput(inputfield, errorLabelId) {
    let errorElement = document.getElementById(errorLabelId);
    inputfield.setCustomValidity('');
    inputfield.setAttribute("style", "border: 2px solid #00AA00");
    errorElement.setAttribute('hidden',true);
    inputfield.checkValidity();
    //console.log(inputfield.validity);
}
/**
 *
 * @param {*} inputfield är input elementet som ska valideras
 * @param {*} errorLabelId är id på den error label som visar information om felet
 * @param {*} errorTextMessage ett message som visas när ett fel inträffats 
 * 
 * Först hämtas error label id 
 * Sen sätter vi html5 costomvalidity till ett space för att inte den ska visas
 * Efter det görs kontroll på viktet typ av fel det är och väljer felmeddelande baserat på det
 * Sedan visas error fältet
 * Och till sists sätts en röd färg runt input fältet
 */
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

