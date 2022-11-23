import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './trade-shop.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITradeShopDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TradeShopDetail = (props: ITradeShopDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tradeShopEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tradeShopDetailsHeading">TradeShop</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{tradeShopEntity.id}</dd>
          <dt>
            <span id="category">Category</span>
            <UncontrolledTooltip target="category">категория</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopEntity.category}</dd>
          <dt>
            <span id="priceDiapozon">Price Diapozon</span>
            <UncontrolledTooltip target="priceDiapozon">ценовой диапозон</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopEntity.priceDiapozon}</dd>
          <dt>
            <span id="currentPrice">Current Price</span>
            <UncontrolledTooltip target="currentPrice">текущая цена</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopEntity.currentPrice}</dd>
          <dt>
            <span id="whiceLineFromAllCountLines">Whice Line From All Count Lines</span>
            <UncontrolledTooltip target="whiceLineFromAllCountLines">место- какая строка из пяти</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopEntity.whiceLineFromAllCountLines}</dd>
          <dt>
            <span id="tgUserIdWinner">Tg User Id Winner</span>
            <UncontrolledTooltip target="tgUserIdWinner">айди текущего победителя</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopEntity.tgUserIdWinner}</dd>
          <dt>
            <span id="inWhatDateWillPostThisLinks">In What Date Will Post This Links</span>
            <UncontrolledTooltip target="inWhatDateWillPostThisLinks">дата размещения</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopEntity.inWhatDateWillPostThisLinks}</dd>
          <dt>
            <span id="dateFinishTorgs">Date Finish Torgs</span>
            <UncontrolledTooltip target="dateFinishTorgs">дата окончания торгов</UncontrolledTooltip>
          </dt>
          <dd>
            {tradeShopEntity.dateFinishTorgs ? (
              <TextFormat value={tradeShopEntity.dateFinishTorgs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{tradeShopEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{tradeShopEntity.date1 ? <TextFormat value={tradeShopEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{tradeShopEntity.date2 ? <TextFormat value={tradeShopEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{tradeShopEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{tradeShopEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{tradeShopEntity.boolean1 ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/trade-shop" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/trade-shop/${tradeShopEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tradeShop }: IRootState) => ({
  tradeShopEntity: tradeShop.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TradeShopDetail);
