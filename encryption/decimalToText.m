function result = decimalToText(numStr, chunkSize)
    result = ""; % Initialize result FIRST

    if isempty(result)
        result = "[Error: Empty input]";
        return;
    end

    result = ""; % establishes result string
    alphabet = 'abcdefghijklmnopqrstuvwxyz '; % establishes alphabet to be used
    base = java.math.BigInteger('27'); % base number for division
    num = java.math.BigInteger(numStr); % convert the numStr into a BigInt
    zero = java.math.BigInteger('0');

    while num.compareTo(zero) > 0
        [num, rem] = divideAndRemainder(num, base); % separate last character
        remIndex = rem.intValue() + 1; % add character to result string
        result = alphabet(remIndex) + result; % divide number and round down to remove added character
    end

    if isempty(result)
        result = " ";
    end

    while mod(strlength(result), chunkSize) ~= 0
        result = "a" + result;
    end
end

function [quotient, remainder] = divideAndRemainder(bigint, divisor)
    quotient = bigint.divide(divisor);
    remainder = bigint.remainder(divisor);
end