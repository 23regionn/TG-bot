import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './members-trade-deal-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMembersTradeDealLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MembersTradeDealLogDetail = (props: IMembersTradeDealLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { membersTradeDealLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="membersTradeDealLogDetailsHeading">MembersTradeDealLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{membersTradeDealLogEntity.id}</dd>
          <dt>
            <span id="tgUserIdCurrent">Tg User Id Current</span>
            <UncontrolledTooltip target="tgUserIdCurrent">айди текущего пользователя</UncontrolledTooltip>
          </dt>
          <dd>{membersTradeDealLogEntity.tgUserIdCurrent}</dd>
          <dt>
            <span id="priceOffer">Price Offer</span>
            <UncontrolledTooltip target="priceOffer">предложение цена</UncontrolledTooltip>
          </dt>
          <dd>{membersTradeDealLogEntity.priceOffer}</dd>
          <dt>
            <span id="currentDate">Current Date</span>
            <UncontrolledTooltip target="currentDate">дата текущая</UncontrolledTooltip>
          </dt>
          <dd>
            {membersTradeDealLogEntity.currentDate ? (
              <TextFormat value={membersTradeDealLogEntity.currentDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="isWinner">Is Winner</span>
            <UncontrolledTooltip target="isWinner">победитель?</UncontrolledTooltip>
          </dt>
          <dd>{membersTradeDealLogEntity.isWinner ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{membersTradeDealLogEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>
            {membersTradeDealLogEntity.date1 ? (
              <TextFormat value={membersTradeDealLogEntity.date1} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>
            {membersTradeDealLogEntity.date2 ? (
              <TextFormat value={membersTradeDealLogEntity.date2} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{membersTradeDealLogEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{membersTradeDealLogEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{membersTradeDealLogEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Members Trade Deal</dt>
          <dd>{membersTradeDealLogEntity.membersTradeDeal ? membersTradeDealLogEntity.membersTradeDeal.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/members-trade-deal-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/members-trade-deal-log/${membersTradeDealLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ membersTradeDealLog }: IRootState) => ({
  membersTradeDealLogEntity: membersTradeDealLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MembersTradeDealLogDetail);
