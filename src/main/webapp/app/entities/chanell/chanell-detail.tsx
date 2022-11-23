import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './chanell.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IChanellDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ChanellDetail = (props: IChanellDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { chanellEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="chanellDetailsHeading">Chanell</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{chanellEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{chanellEntity.name}</dd>
          <dt>
            <span id="link">Link</span>
          </dt>
          <dd>{chanellEntity.link}</dd>
          <dt>
            <span id="score">Score</span>
          </dt>
          <dd>{chanellEntity.score}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{chanellEntity.status}</dd>
          <dt>
            <span id="countSubscribers">Count Subscribers</span>
          </dt>
          <dd>{chanellEntity.countSubscribers}</dd>
          <dt>
            <span id="quailityFromAnotherSources">Quaility From Another Sources</span>
          </dt>
          <dd>{chanellEntity.quailityFromAnotherSources}</dd>
          <dt>
            <span id="priceDiapozon">Price Diapozon</span>
          </dt>
          <dd>{chanellEntity.priceDiapozon}</dd>
          <dt>
            <span id="isModerate">Is Moderate</span>
          </dt>
          <dd>{chanellEntity.isModerate ? 'true' : 'false'}</dd>
          <dt>
            <span id="showChanellInTopByCategory">Show Chanell In Top By Category</span>
          </dt>
          <dd>{chanellEntity.showChanellInTopByCategory ? 'true' : 'false'}</dd>
          <dt>
            <span id="region">Region</span>
          </dt>
          <dd>{chanellEntity.region}</dd>
          <dt>
            <span id="city">City</span>
          </dt>
          <dd>{chanellEntity.city}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{chanellEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="currentDate">Current Date</span>
            <UncontrolledTooltip target="currentDate">текущая дата</UncontrolledTooltip>
          </dt>
          <dd>
            {chanellEntity.currentDate ? <TextFormat value={chanellEntity.currentDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{chanellEntity.date1 ? <TextFormat value={chanellEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{chanellEntity.date2 ? <TextFormat value={chanellEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{chanellEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{chanellEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{chanellEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>T G User</dt>
          <dd>{chanellEntity.tGUser ? chanellEntity.tGUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/chanell" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/chanell/${chanellEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ chanell }: IRootState) => ({
  chanellEntity: chanell.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ChanellDetail);
