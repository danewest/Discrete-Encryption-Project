% functions
function result = textToDecimal(text, chunkSize)
    text = lower(text); % converts message to lowercase
    alphabet = 'abcdefghijklmnopqrstuvwxyz '; % establishes alphabet to be used
    values = arrayfun(@(character) find(alphabet == character, 1) - 1, text); % for the length of text: finds position of matching character in the alphabet, subtracts one to accounts for mathlab beginning index at 1 rather than 0
    result = sum(values .* (27 .^ (length(values)-1:-1:0))); % converts base-27 to decimal: sum of values * 27 ^ the length of values(3)-1, subtracting 1 each time, until the value of 0 is reached
end

function result = decimalToText(num, chunkSize)
    result = ''; % establishes result string
    alphabet = 'abcdefghijklmnopqrstuvwxyz '; % establishes alphabet to be used
    base = 27; % base number for division
    while num > 0
        remainder = mod(num, base); % separate last character
        result = [alphabet(remainder+1), result]; % add character to result string
        num = floor(num/base); % divide number and round down to remove added character
    end
    if isempty(result)
        result = ' ';
    end
end