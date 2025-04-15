function result = modExp(baseStr, exponent, modulus)
    % Convert baseStr into a java BigInteger
    base = java.math.BigInteger(baseStr); % Convert the string input into a number

    % Convert exponent and modulus into BigInteger as well
    exp = java.math.BigInteger(num2str(exponent));
    modn = java.math.BigInteger(num2str(modulus));

    % Compute base^exponent mod modulus
    resultBigInt = base.modPow(exp, modn);

    % Convert resultBigInt back into a string
    result = char(resultBigInt.toString()); % Return the new string
end
