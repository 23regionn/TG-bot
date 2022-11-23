import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './chanell-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IChanellLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ChanellLogDetail = (props: IChanellLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { chanellLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="chanellLogDetailsHeading">ChanellLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{chanellLogEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{chanellLogEntity.name}</dd>
          <dt>
            <span id="link">Link</span>
          </dt>
          <dd>{chanellLogEntity.link}</dd>
          <dt>
            <span id="score">Score</span>
          </dt>
          <dd>{chanellLogEntity.score}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{chanellLogEntity.status}</dd>
          <dt>
            <span id="countSubscribers">Count Subscribers</span>
          </dt>
          <dd>{chanellLogEntity.countSubscribers}</dd>
          <dt>
            <span id="quailityFromAnotherSources">Quaility From Another Sources</span>
          </dt>
          <dd>{chanellLogEntity.quailityFromAnotherSources}</dd>
          <dt>
            <span id="priceDiapozon">Price Diapozon</span>
          </dt>
          <dd>{chanellLogEntity.priceDiapozon}</dd>
          <dt>
            <span id="isModerate">Is Moderate</span>
          </dt>
          <dd>{chanellLogEntity.isModerate ? 'true' : 'false'}</dd>
          <dt>
            <span id="showChanellInTopByCategory">Show Chanell In Top By Category</span>
          </dt>
          <dd>{chanellLogEntity.showChanellInTopByCategory ? 'true' : 'false'}</dd>
          <dt>
            <span id="region">Region</span>
          </dt>
          <dd>{chanellLogEntity.region}</dd>
          <dt>
            <span id="city">City</span>
          </dt>
          <dd>{chanellLogEntity.city}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{chanellLogEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="currentDate">Current Date</span>
            <UncontrolledTooltip target="currentDate">текущая дата</UncontrolledTooltip>
          </dt>
          <dd>
            {chanellLogEntity.currentDate ? <TextFormat value={chanellLogEntity.currentDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{chanellLogEntity.date1 ? <TextFormat value={chanellLogEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{chanellLogEntity.date2 ? <TextFormat value={chanellLogEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{chanellLogEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{chanellLogEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{chanellLogEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Chanell</dt>
          <dd>{chanellLogEntity.chanell ? chanellLogEntity.chanell.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/chanell-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/chanell-log/${chanellLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ chanellLog }: IRootState) => ({
  chanellLogEntity: chanellLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ChanellLogDetail);
