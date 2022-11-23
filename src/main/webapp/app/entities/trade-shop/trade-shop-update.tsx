import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './trade-shop.reducer';
import { ITradeShop } from 'app/shared/model/trade-shop.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITradeShopUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TradeShopUpdate = (props: ITradeShopUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { tradeShopEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/trade-shop');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
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
        ...tradeShopEntity,
        ...values,
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
          <h2 id="gidApp.tradeShop.home.createOrEditLabel" data-cy="TradeShopCreateUpdateHeading">
            Create or edit a TradeShop
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tradeShopEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="trade-shop-id">ID</Label>
                  <AvInput id="trade-shop-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="categoryLabel" for="trade-shop-category">
                  Category
                </Label>
                <AvField id="trade-shop-category" data-cy="category" type="text" name="category" />
                <UncontrolledTooltip target="categoryLabel">категория</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="priceDiapozonLabel" for="trade-shop-priceDiapozon">
                  Price Diapozon
                </Label>
                <AvField
                  id="trade-shop-priceDiapozon"
                  data-cy="priceDiapozon"
                  type="string"
                  className="form-control"
                  name="priceDiapozon"
                />
                <UncontrolledTooltip target="priceDiapozonLabel">ценовой диапозон</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="currentPriceLabel" for="trade-shop-currentPrice">
                  Current Price
                </Label>
                <AvField id="trade-shop-currentPrice" data-cy="currentPrice" type="string" className="form-control" name="currentPrice" />
                <UncontrolledTooltip target="currentPriceLabel">текущая цена</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="whiceLineFromAllCountLinesLabel" for="trade-shop-whiceLineFromAllCountLines">
                  Whice Line From All Count Lines
                </Label>
                <AvField
                  id="trade-shop-whiceLineFromAllCountLines"
                  data-cy="whiceLineFromAllCountLines"
                  type="string"
                  className="form-control"
                  name="whiceLineFromAllCountLines"
                />
                <UncontrolledTooltip target="whiceLineFromAllCountLinesLabel">место- какая строка из пяти</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="tgUserIdWinnerLabel" for="trade-shop-tgUserIdWinner">
                  Tg User Id Winner
                </Label>
                <AvField
                  id="trade-shop-tgUserIdWinner"
                  data-cy="tgUserIdWinner"
                  type="string"
                  className="form-control"
                  name="tgUserIdWinner"
                />
                <UncontrolledTooltip target="tgUserIdWinnerLabel">айди текущего победителя</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="inWhatDateWillPostThisLinksLabel" for="trade-shop-inWhatDateWillPostThisLinks">
                  In What Date Will Post This Links
                </Label>
                <AvField
                  id="trade-shop-inWhatDateWillPostThisLinks"
                  data-cy="inWhatDateWillPostThisLinks"
                  type="string"
                  className="form-control"
                  name="inWhatDateWillPostThisLinks"
                />
                <UncontrolledTooltip target="inWhatDateWillPostThisLinksLabel">дата размещения</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dateFinishTorgsLabel" for="trade-shop-dateFinishTorgs">
                  Date Finish Torgs
                </Label>
                <AvInput
                  id="trade-shop-dateFinishTorgs"
                  data-cy="dateFinishTorgs"
                  type="datetime-local"
                  className="form-control"
                  name="dateFinishTorgs"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.tradeShopEntity.dateFinishTorgs)}
                />
                <UncontrolledTooltip target="dateFinishTorgsLabel">дата окончания торгов</UncontrolledTooltip>
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput id="trade-shop-isDelete" data-cy="isDelete" type="checkbox" className="form-check-input" name="isDelete" />
                  Is Delete
                </Label>
                <UncontrolledTooltip target="isDeleteLabel">удаленный</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="trade-shop-date1">
                  Date 1
                </Label>
                <AvInput
                  id="trade-shop-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.tradeShopEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="trade-shop-date2">
                  Date 2
                </Label>
                <AvInput
                  id="trade-shop-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.tradeShopEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="trade-shop-long1">
                  Long 1
                </Label>
                <AvField id="trade-shop-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="trade-shop-string1">
                  String 1
                </Label>
                <AvField id="trade-shop-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="trade-shop-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/trade-shop" replace color="info">
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
  tradeShopEntity: storeState.tradeShop.entity,
  loading: storeState.tradeShop.loading,
  updating: storeState.tradeShop.updating,
  updateSuccess: storeState.tradeShop.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TradeShopUpdate);
