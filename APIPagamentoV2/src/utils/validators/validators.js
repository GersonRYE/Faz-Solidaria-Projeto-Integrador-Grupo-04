
const isValidCardNumber = (cardNumber) => {
    if (isNaN(cardNumber)) {
        return false
    }

    const value = String(cardNumber)
    let sum = 0;
    let shouldDouble = false;
 
    for (let i = value.length - 1; i >= 0; i--) {
        let digit = parseInt(value.charAt(i));

        if (shouldDouble) {
            if ((digit *= 2) > 9) digit -= 9;
        }

        sum += digit;
        shouldDouble = !shouldDouble;
    }
    return (sum % 10) == 0;
}

module.exports = {
    isValidCardNumber
}