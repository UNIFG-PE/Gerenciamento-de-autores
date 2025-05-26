-- Use the biblioteca database
USE biblioteca;

-- 1. Add a JSON column for keywords to the existing autores table
ALTER TABLE autores
ADD COLUMN keywords JSON;

-- 2. Examples of inserting and updating authors with keywords

-- Example 1: Insert a new author with keywords
INSERT INTO autores (nome, informacoes, keywords) 
VALUES ('Gabriel Garcia Marquez', 'Colombian novelist and Nobel Prize winner', 
        '["Magical Realism", "Literary Fiction", "Latin American Literature"]');

-- Example 2: Insert another author with different keywords
INSERT INTO autores (nome, informacoes, keywords) 
VALUES ('Stephen King', 'American author of horror, supernatural fiction, and fantasy', 
        '["Horror", "Thriller", "Supernatural", "Fantasy"]');

-- Example 3: Update an existing author to add keywords
-- (Assuming author with ID 1 already exists)
UPDATE autores 
SET keywords = '["Romance", "Historical Fiction", "Drama"]'
WHERE id = 1;

-- 3. Query and manipulate keywords

-- Example 1: Find all authors who write "Fantasy"
SELECT id, nome, keywords 
FROM autores 
WHERE JSON_CONTAINS(keywords, '"Fantasy"', '$');

-- Example 2: Find authors who write either Horror OR Thriller
SELECT id, nome, keywords 
FROM autores 
WHERE JSON_CONTAINS(keywords, '"Horror"', '$') 
   OR JSON_CONTAINS(keywords, '"Thriller"', '$');

-- Example 3: Count how many keywords each author has
SELECT id, nome, JSON_LENGTH(keywords) AS keyword_count
FROM autores;

-- Example 4: Get a specific keyword by position (0-based index)
SELECT id, nome, JSON_EXTRACT(keywords, '$[0]') AS primary_genre
FROM autores;

-- Example 5: Add a new keyword to an author (append to array)
UPDATE autores 
SET keywords = JSON_ARRAY_APPEND(
    IFNULL(keywords, JSON_ARRAY()), -- Handle NULL case
    '$', 
    'Science Fiction'
)
WHERE id = 2;

-- Example 6: Remove a keyword from an author
-- First, find the position of the keyword to remove
SET @author_id = 2;
SET @keyword_to_remove = 'Thriller';

-- Then remove it using JSON_REMOVE with the position
UPDATE autores
SET keywords = JSON_REMOVE(
    keywords,
    JSON_UNQUOTE(JSON_SEARCH(keywords, 'one', @keyword_to_remove))
)
WHERE id = @author_id
  AND JSON_CONTAINS(keywords, CONCAT('"', @keyword_to_remove, '"'), '$');

-- Example 7: Extract all keywords as a comma-separated string
SELECT id, nome, 
       JSON_UNQUOTE(
           REPLACE(
               REPLACE(
                   JSON_EXTRACT(keywords, '$'), 
                   '[', ''
               ), 
               ']', ''
           )
       ) AS keyword_list
FROM autores;

-- Example 8: Find authors with at least 3 keywords
SELECT id, nome, JSON_LENGTH(keywords) AS keyword_count
FROM autores
WHERE JSON_LENGTH(keywords) >= 3;

/*
Additional notes on using JSON arrays in MySQL:

1. JSON functions require MySQL 5.7 or later
2. JSON arrays are more efficient for storage but less efficient for searching compared to normalized tables
3. There's no referential integrity with this approach
4. If you need to filter frequently by keywords, consider adding a generated column or index
   For example:
   
   ALTER TABLE autores
   ADD COLUMN has_fantasy BOOLEAN GENERATED ALWAYS AS (JSON_CONTAINS(keywords, '"Fantasy"', '$')) VIRTUAL,
   ADD INDEX idx_has_fantasy (has_fantasy);
*/

