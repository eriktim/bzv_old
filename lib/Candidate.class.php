<?php

class Candidate extends Base {

  const TABLE_NAME = 'bzv_candidates';
  const COLUMN_NAME = 'candidateid';

  public function get_name() {
    return parent::get_value('name');
  }

  public function get_peasant() {
    return parent::get_object(Peasant::c());
  }

  public function get_date_elimination() {
    $date = parent::get_value('date_elimination');
    if ($date) {
      return strtotime($date);
    }
    return false;
  }

  public function is_eliminated() {
    $date = parent::get_value('date_elimination');
    return !!$date;
  }

  public function as_html($votes) {
    $peasant = $this->get_peasant();
    $eliminated = $this->is_eliminated();
    $disabled = '';
    $icon = '';
    if ($eliminated) {
      $disabled = ' disabled';
    } else {
      $icon = '';
      foreach ($votes as $vote) {
        if ($vote->get_candidate()->id == $this->id) {
          switch ($vote->get_type()->id) {
            case VoteType::BAD:
              $icon = 'ico-bad';
              break;
            case VoteType::GOOD:
              $icon = 'ico-good';
              break;
            case VoteType::HEART:
              $icon = 'ico-heart';
              break;
          }
          break;
        }
      }
    }
    return '<div id="' . $this->id . '" class="candidate pea' . $peasant->id
        . ' can' . $this->id . $disabled . '">'
        . '<div class="badge icon ' . $icon . '"></div></div>';
  }

  public function vote($type) {
    global $USER;
    $period = VotePeriod::get_current();
    if (!$period) {
      return false;
    }

    $votes = Vote::find($this, $period);
    if (count($votes) > 0) {
      $vote = $votes[0];
      for ($i = 1; $i < count($votes); $i++) {
        $votes[$i]->delete();
      }
      if ($type->id > 0) {
        $data = array(VoteType::COLUMN_NAME => $type->id);
        return $vote->update($data);
      } else {
        return $vote->delete();
      }
    } else {
      return !!Vote::create($this, $period, $type);
    }
  }

  public static function get_all($var) {
    $q = NULL;
    $data = array();

    switch (get_class($var)) {
      case Peasant::c():
        $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE ' . Peasant::COLUMN_NAME. '=:column ORDER BY id';
        $data['column'] = $var->id;
        break;
      default:
        $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE year=:year ORDER BY id';
        $data['year'] = (int) $var;
        break;
    }

    return parent::fetch_array($q, $data);
  }

}
