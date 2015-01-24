<?php

require_once '../base.php';

if (!isset($_POST[User::COLUMN_NAME],
    $_POST[Candidate::COLUMN_NAME],
    $_POST[VoteType::COLUMN_NAME])) {
  header('HTTP/1.1 400 Bad Request');
  exit;
}

if ($USER->id != $_POST[User::COLUMN_NAME]) {
  header('HTTP/1.1 403 Forbidden');
  exit;
}

$candidate = new Candidate((int) $_POST[Candidate::COLUMN_NAME]);
if (!$candidate || !$candidate->id) {
  header('HTTP/1.1 400 Bad Request');
  echo 'Invalid candidate';
  exit;
}

$type = new VoteType((int) $_POST[VoteType::COLUMN_NAME]);

if (!$candidate->vote($type)) {
  header('HTTP/1.1 400 Bad Request');
  echo 'Failed to process your vote';
  exit;
}
