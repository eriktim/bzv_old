<?php

class Vote extends Base {

  const TABLE_NAME = 'bzv_votes';
  const COLUMN_NAME = 'voteid';

  public function delete() {
    global $db;
    $data = array('id' => $this->id);
    $q = 'DELETE FROM ' . self::TABLE_NAME . ' WHERE id=:id';
    $st = $db->prepare($q);
    return $st->execute($data);
  }

  public function get_candidate() {
    return parent::get_object(Candidate::c());
  }

  public function get_peasant() {
    $candidate = $this->get_candidate();
    if (!$candidate) {
      return NULL;
    }
    return $candidate->get_peasant();
  }

  public function get_period() {
    return parent::get_object(VotePeriod::c());
  }

  public function get_points() {
    $period = $this->get_period();
    $time = $period->get_date_reference();
    if (!$time) {
      return 0;
    }
    $points = 0;
    $candidate = $this->get_candidate();
    $type = $this->get_type();
    if ($type->id === VoteType::HEART) {
      $winner = $this->get_peasant()->get_winner();
      if ($winner) {
        if ($winner->id == $candidate->id) {
          // add bonus points
          $points += $period->get_vote_count();
        }
      }
    }
    $eliminationTime = $candidate->get_date_elimination();
    if ($eliminationTime) {
      $bad = $type->id === VoteType::BAD;
      if ($eliminationTime < $time) {
        if ($bad) {
          $points++;
        }
      } elseif (!$bad) {
        $points++;
      }
    }
    return $points;
  }

  public function get_type() {
    return parent::get_object(VoteType::c());
  }

  public function update($data) {
var_dump($data);
    global $db;
    $changes = array();
    foreach ($data as $key=>$value) {
      $changes[] = $key . '=:' . $key;
    }
    $data['id'] = $this->id;
    $q = 'UPDATE ' . self::TABLE_NAME . ' SET date_voted=NOW(), '
        . implode(',', $changes) . ' WHERE id=:id';
    $st = $db->prepare($q);
    return $st->execute($data);
  }

  public static function create($candidate, $period, $type) {
    if ($type->id == 0) {
      return true;
    }

    global $USER, $db;

    $data = array(
      User::COLUMN_NAME => $USER->id,
      Candidate::COLUMN_NAME => $candidate->id,
      VotePeriod::COLUMN_NAME => $period->id,
      VoteType::COLUMN_NAME => $type->id
    );
    $keys = array();
    $values = array();
    foreach ($data as $key=>$value) {
      $keys[] = $key;
      $values[] = ':' . $key;
    }

    $q = 'INSERT INTO ' . self::TABLE_NAME
        . ' (date_voted, ' . implode(',', $keys) . ')'
        . ' VALUES (NOW(), ' . implode(',', $values) . ')'
        . ' RETURNING id';
    $st = $db->prepare($q);
    if (!$st->execute($data)) {
      return false;
    }

    $st->setFetchMode(PDO::FETCH_ASSOC);
    $voteData = $st->fetch();
    return new Vote((int) $voteData['id']);
  }

  public static function find($candidate, $period) {
    global $USER;
    $data = array(
      User::COLUMN_NAME => $USER->id,
      Candidate::COLUMN_NAME => $candidate->id,
      VotePeriod::COLUMN_NAME => $period->id
    );
    $q = 'SELECT id FROM ' . self::TABLE_NAME
        . ' WHERE ' . User::COLUMN_NAME . '=:' . User::COLUMN_NAME
        . ' AND ' . Candidate::COLUMN_NAME . '=:' . Candidate::COLUMN_NAME
        . ' AND ' . VotePeriod::COLUMN_NAME . '=:' . VotePeriod::COLUMN_NAME
        . ' ORDER BY id';

    return parent::fetch_array($q, $data);
  }

  public static function get_by_user_in_period($user, $period) {
    $data = array(
      User::COLUMN_NAME => $user->id,
      VotePeriod::COLUMN_NAME => $period->id
    );
    $q = 'SELECT id FROM ' . self::TABLE_NAME
        . ' WHERE ' . User::COLUMN_NAME . '=:' . User::COLUMN_NAME
        . ' AND ' . VotePeriod::COLUMN_NAME . '=:' . VotePeriod::COLUMN_NAME
        . ' ORDER BY id';

    return parent::fetch_array($q, $data);
  }

  public static function get_all($year) {
    $data = array('year' => $year);
    $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE year=:year ORDER BY id';

    return parent::fetch_array($q, $data);
  }
  
}
