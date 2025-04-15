function result = textToDecimal(text, chunkSize)
    % converts message to lowercase
    text = lower(text);
    % Removes anything not a letter or space and makes sure the message is an array of chars
    text = regexprep(text, '[^a-z ]', '');
    text = char(text);

    % establishes alphabet to be used
    alphabet = 'abcdefghijklmnopqrstuvwxyz ';

    % for the length of text: finds position of matching character in the alphabet,
    % subtracts one to accounts for mathlab beginning index at 1 rather than 0
    values = arrayfun(@(character) getIndex(alphabet, character), text);

    % converts base-27 to decimal: sum of values * 27 ^ the length of values(3)-1,
    % subtracting 1 each time, until the value of 0 is reached
    result = char(string(sum(values .* (27 .^ (length(values)-1:-1:0)))));
end

% below function helps ensure that each call to arrayfun returns exactly
% one scalar to avoid "UniformOutput" complaints
function idx = getIndex(alphabet, character)
    pos = find(alphabet == character, 1);
    if isempty(pos)
        error("Character '%s' not found in the alphabet.", character);
    end
    idx = pos - 1;
end