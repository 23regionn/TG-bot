import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './trade-shop-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITradeShopLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TradeShopLogDetail = (props: ITradeShopLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tradeShopLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tradeShopLogDetailsHeading">TradeShopLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{tradeShopLogEntity.id}</dd>
          <dt>
            <span id="category">Category</span>
            <UncontrolledTooltip target="category">категория</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopLogEntity.category}</dd>
          <dt>
            <span id="priceDiapozon">Price Diapozon</span>
            <UncontrolledTooltip target="priceDiapozon">ценовой диапозон</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopLogEntity.priceDiapozon}</dd>
          <dt>
            <span id="currentPrice">Current Price</span>
            <UncontrolledTooltip target="currentPrice">текущая цена</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopLogEntity.currentPrice}</dd>
          <dt>
            <span id="whiceLineFromAllCountLines">Whice Line From All Count Lines</span>
            <UncontrolledTooltip target="whiceLineFromAllCountLines">место- какая строка из пяти</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopLogEntity.whiceLineFromAllCountLines}</dd>
          <dt>
            <span id="tgUserIdWinner">Tg User Id Winner</span>
            <UncontrolledTooltip target="tgUserIdWinner">айди текущего победителя</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopLogEntity.tgUserIdWinner}</dd>
          <dt>
            <span id="inWhatDateWillPostThisLinks">In What Date Will Post This Links</span>
            <UncontrolledTooltip target="inWhatDateWillPostThisLinks">дата размещения</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopLogEntity.inWhatDateWillPostThisLinks}</dd>
          <dt>
            <span id="dateFinishTorgs">Date Finish Torgs</span>
            <UncontrolledTooltip target="dateFinishTorgs">дата окончания торгов</UncontrolledTooltip>
          </dt>
          <dd>
            {tradeShopLogEntity.dateFinishTorgs ? (
              <TextFormat value={tradeShopLogEntity.dateFinishTorgs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopLogEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{tradeShopLogEntity.date1 ? <TextFormat value={tradeShopLogEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{tradeShopLogEntity.date2 ? <TextFormat value={tradeShopLogEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{tradeShopLogEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{tradeShopLogEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{tradeShopLogEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Trade Shop</dt>
          <dd>{tradeShopLogEntity.tradeShop ? tradeShopLogEntity.tradeShop.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/trade-shop-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/trade-shop-log/${tradeShopLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tradeShopLog }: IRootState) => ({
  tradeShopLogEntity: tradeShopLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TradeShopLogDetail);
