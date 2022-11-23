import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITradeShop } from 'app/shared/model/trade-shop.model';
import { getEntities as getTradeShops } from 'app/entities/trade-shop/trade-shop.reducer';
import { getEntity, updateEntity, createEntity, reset } from './trade-shop-log.reducer';
import { ITradeShopLog } from 'app/shared/model/trade-shop-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITradeShopLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TradeShopLogUpdate = (props: ITradeShopLogUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { tradeShopLogEntity, tradeShops, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/trade-shop-log');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTradeShops();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateFinishTorgs = convertDateTimeToServer(values.dateFinishTorgs);
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...tradeShopLogEntity,
        ...values,
        tradeShop: tradeShops.find(it => it.id.toString() === values.tradeShopId.toString()),
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gidApp.tradeShopLog.home.createOrEditLabel" data-cy="TradeShopLogCreateUpdateHeading">
            Create or edit a TradeShopLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tradeShopLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="trade-shop-log-id">ID</Label>
                  <AvInput id="trade-shop-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="categoryLabel" for="trade-shop-log-category">
                  Category
                </Label>
                <AvField id="trade-shop-log-category" data-cy="category" type="text" name="category" />
                <UncontrolledTooltip target="categoryLabel">категория</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="priceDiapozonLabel" for="trade-shop-log-priceDiapozon">
                  Price Diapozon
                </Label>
                <AvField
                  id="trade-shop-log-priceDiapozon"
                  data-cy="priceDiapozon"
                  type="string"
                  className="form-control"
                  name="priceDiapozon"
                />
                <UncontrolledTooltip target="priceDiapozonLabel">ценовой диапозон</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="currentPriceLabel" for="trade-shop-log-currentPrice">
                  Current Price
                </Label>
                <AvField
                  id="trade-shop-log-currentPrice"
                  data-cy="currentPrice"
                  type="string"
                  className="form-control"
                  name="currentPrice"
                />
                <UncontrolledTooltip target="currentPriceLabel">текущая цена</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="whiceLineFromAllCountLinesLabel" for="trade-shop-log-whiceLineFromAllCountLines">
                  Whice Line From All Count Lines
                </Label>
                <AvField
                  id="trade-shop-log-whiceLineFromAllCountLines"
                  data-cy="whiceLineFromAllCountLines"
                  type="string"
                  className="form-control"
                  name="whiceLineFromAllCountLines"
                />
                <UncontrolledTooltip target="whiceLineFromAllCountLinesLabel">место- какая строка из пяти</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="tgUserIdWinnerLabel" for="trade-shop-log-tgUserIdWinner">
                  Tg User Id Winner
                </Label>
                <AvField
                  id="trade-shop-log-tgUserIdWinner"
                  data-cy="tgUserIdWinner"
                  type="string"
                  className="form-control"
                  name="tgUserIdWinner"
                />
                <UncontrolledTooltip target="tgUserIdWinnerLabel">айди текущего победителя</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="inWhatDateWillPostThisLinksLabel" for="trade-shop-log-inWhatDateWillPostThisLinks">
                  In What Date Will Post This Links
                </Label>
                <AvField
                  id="trade-shop-log-inWhatDateWillPostThisLinks"
                  data-cy="inWhatDateWillPostThisLinks"
                  type="string"
                  className="form-control"
                  name="inWhatDateWillPostThisLinks"
                />
                <UncontrolledTooltip target="inWhatDateWillPostThisLinksLabel">дата размещения</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dateFinishTorgsLabel" for="trade-shop-log-dateFinishTorgs">
                  Date Finish Torgs
                </Label>
                <AvInput
                  id="trade-shop-log-dateFinishTorgs"
                  data-cy="dateFinishTorgs"
                  type="datetime-local"
                  className="form-control"
                  name="dateFinishTorgs"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.tradeShopLogEntity.dateFinishTorgs)}
                />
                <UncontrolledTooltip target="dateFinishTorgsLabel">дата окончания торгов</UncontrolledTooltip>
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput id="trade-shop-log-isDelete" data-cy="isDelete" type="checkbox" className="form-check-input" name="isDelete" />
                  Is Delete
                </Label>
                <UncontrolledTooltip target="isDeleteLabel">удаленный</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="trade-shop-log-date1">
                  Date 1
                </Label>
                <AvInput
                  id="trade-shop-log-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.tradeShopLogEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="trade-shop-log-date2">
                  Date 2
                </Label>
                <AvInput
                  id="trade-shop-log-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.tradeShopLogEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="trade-shop-log-long1">
                  Long 1
                </Label>
                <AvField id="trade-shop-log-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="trade-shop-log-string1">
                  String 1
                </Label>
                <AvField id="trade-shop-log-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="trade-shop-log-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="trade-shop-log-tradeShop">Trade Shop</Label>
                <AvInput id="trade-shop-log-tradeShop" data-cy="tradeShop" type="select" className="form-control" name="tradeShopId">
                  <option value="" key="0" />
                  {tradeShops
                    ? tradeShops.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/trade-shop-log" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  tradeShops: storeState.tradeShop.entities,
  tradeShopLogEntity: storeState.tradeShopLog.entity,
  loading: storeState.tradeShopLog.loading,
  updating: storeState.tradeShopLog.updating,
  updateSuccess: storeState.tradeShopLog.updateSuccess,
});

const mapDispatchToProps = {
  getTradeShops,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TradeShopLogUpdate);
