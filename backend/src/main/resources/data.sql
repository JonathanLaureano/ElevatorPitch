-- Initialize database schema for broken elevator assistance system
-- This script will be executed on application startup

-- Insert predefined questions with answer mappings
INSERT INTO questions (id, text, question_order) VALUES 
(1, 'Are you or someone in your household comfortable and able to troubleshoot?', 1),
(2, 'Are you currently at the property with the elevator?', 2),
(3, 'What type of elevator do you have?', 3);

-- Insert options for question 1
INSERT INTO question_options (question_id, option_text) VALUES 
(1, 'Yes'),
(1, 'No');

-- Insert options for question 2
INSERT INTO question_options (question_id, option_text) VALUES 
(2, 'Yes'),
(2, 'No');

-- Insert options for question 3
INSERT INTO question_options (question_id, option_text) VALUES 
(3, 'Traction'),
(3, 'Hydraulic'),
(3, 'Unsure');

-- Insert answer mappings for question 1
INSERT INTO question_answer_mappings (question_id, answer_option, next_page) VALUES 
(1, 'Yes', 'question/2'),
(1, 'No', 'send-tech');

-- Insert answer mappings for question 2
INSERT INTO question_answer_mappings (question_id, answer_option, next_page) VALUES 
(2, 'Yes', 'question/3'),
(2, 'No', 'home-popup');

-- Insert answer mappings for question 3
INSERT INTO question_answer_mappings (question_id, answer_option, next_page) VALUES 
(3, 'Traction', 'question/4'),
(3, 'Hydraulic', 'question/4'),
(3, 'Unsure', 'elevator-type-help');

-- Reset sequences to continue from the highest ID
SELECT setval('questions_id_seq', (SELECT COALESCE(MAX(id), 0) FROM questions));