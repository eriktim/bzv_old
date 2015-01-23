<?php

class Peasant extends Base {

  const TABLE_NAME = 'bzv_peasants';
  const COLUMN_NAME = 'peasantid';

  public function get_name() {
    return parent::get_value('name');
  }

  public function as_html($votes) {
    $icon = 'ico-warning';
    if (count($votes) > 0) {
      $count = $votes[0]->get_period()->get_vote_count();
      $winners = 0;
      $voteCount = 0;
      foreach ($votes as $vote) {
        if ($vote->get_peasant()->id == $this->id) {
          $voteCount++;
          if ($vote->get_type()->id == VoteType::WINNER) {
            $winners++;
          }
        }
      }
      if ($voteCount == $count) {
        if ($winners == 1) {
          $icon = 'ico-heart';
        } elseif (!$winners) {
          $icon = '';
        }
      }
    }
    return '<div class="peasant pea' . $this->id . '">'
        . '<div class="icon ' . $icon . '"></div></div>';
  }

  public function get_candidates() {
    return Candidate::get_all($this);
  }

  public static function get_all($year) {
    $data = array('year' => $year);
    $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE year=:year ORDER BY id';

    return parent::fetch_array($q, $data);
  }

}
