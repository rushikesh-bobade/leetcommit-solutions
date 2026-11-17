function myAtoi(s: string): number {
    let i = 0;
    let sign = 1;
    let result = 0;

    // Skip leading whitespace
    while (i < s.length && s[i] === ' ') {
        i++;
    }

    // Handle sign
    if (i < s.length && (s[i] === '+' || s[i] === '-')) {
        if (s[i] === '-') {
            sign = -1;
        }
        i++;
    }

    // Convert digits
    while (
        i < s.length &&
        s[i] >= '0' &&
        s[i] <= '9'
    ) {
        const digit = Number(s[i]);

        // Check overflow before multiplying
        if (
            result > Math.floor(2147483647 / 10) ||
            (result === Math.floor(2147483647 / 10) &&
                digit > 7)
        ) {
            return sign === 1 ? 2147483647 : -2147483648;
        }

        result = result * 10 + digit;
        i++;
    }

    return result * sign;
}