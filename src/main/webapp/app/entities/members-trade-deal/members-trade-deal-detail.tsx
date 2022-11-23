import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './members-trade-deal.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMembersTradeDealDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MembersTradeDealDetail = (props: IMembersTradeDealDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { membersTradeDealEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="membersTradeDealDetailsHeading">MembersTradeDeal</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{membersTradeDealEntity.id}</dd>
          <dt>
            <span id="tgUserIdCurrent">Tg User Id Current</span>
            <UncontrolledTooltip target="tgUserIdCurrent">айди текущего пользователя</UncontrolledTooltip>
          </dt>
          <dd>{membersTradeDealEntity.tgUserIdCurrent}</dd>
          <dt>
            <span id="priceOffer">Price Offer</span>
            <UncontrolledTooltip target="priceOffer">предложение цена</UncontrolledTooltip>
          </dt>
          <dd>{membersTradeDealEntity.priceOffer}</dd>
          <dt>
            <span id="currentDate">Current Date</span>
            <UncontrolledTooltip target="currentDate">дата текущая</UncontrolledTooltip>
          </dt>
          <dd>
            {membersTradeDealEntity.currentDate ? (
              <TextFormat value={membersTradeDealEntity.currentDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="isWinner">Is Winner</span>
            <UncontrolledTooltip target="isWinner">победитель?</UncontrolledTooltip>
          </dt>
          <dd>{membersTradeDealEntity.isWinner ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{membersTradeDealEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>
            {membersTradeDealEntity.date1 ? <TextFormat value={membersTradeDealEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>
            {membersTradeDealEntity.date2 ? <TextFormat value={membersTradeDealEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{membersTradeDealEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{membersTradeDealEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{membersTradeDealEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Trade Shop</dt>
          <dd>{membersTradeDealEntity.tradeShop ? membersTradeDealEntity.tradeShop.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/members-trade-deal" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/members-trade-deal/${membersTradeDealEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ membersTradeDeal }: IRootState) => ({
  membersTradeDealEntity: membersTradeDeal.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MembersTradeDealDetail);
