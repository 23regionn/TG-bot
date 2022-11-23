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
import { getEntity, updateEntity, createEntity, reset } from './members-trade-deal.reducer';
import { IMembersTradeDeal } from 'app/shared/model/members-trade-deal.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMembersTradeDealUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MembersTradeDealUpdate = (props: IMembersTradeDealUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { membersTradeDealEntity, tradeShops, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/members-trade-deal');
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
    values.currentDate = convertDateTimeToServer(values.currentDate);
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...membersTradeDealEntity,
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
          <h2 id="gidApp.membersTradeDeal.home.createOrEditLabel" data-cy="MembersTradeDealCreateUpdateHeading">
            Create or edit a MembersTradeDeal
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : membersTradeDealEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="members-trade-deal-id">ID</Label>
                  <AvInput id="members-trade-deal-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="tgUserIdCurrentLabel" for="members-trade-deal-tgUserIdCurrent">
                  Tg User Id Current
                </Label>
                <AvField
                  id="members-trade-deal-tgUserIdCurrent"
                  data-cy="tgUserIdCurrent"
                  type="string"
                  className="form-control"
                  name="tgUserIdCurrent"
                />
                <UncontrolledTooltip target="tgUserIdCurrentLabel">айди текущего пользователя</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="priceOfferLabel" for="members-trade-deal-priceOffer">
                  Price Offer
                </Label>
                <AvField id="members-trade-deal-priceOffer" data-cy="priceOffer" type="string" className="form-control" name="priceOffer" />
                <UncontrolledTooltip target="priceOfferLabel">предложение цена</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="currentDateLabel" for="members-trade-deal-currentDate">
                  Current Date
                </Label>
                <AvInput
                  id="members-trade-deal-currentDate"
                  data-cy="currentDate"
                  type="datetime-local"
                  className="form-control"
                  name="currentDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.membersTradeDealEntity.currentDate)}
                />
                <UncontrolledTooltip target="currentDateLabel">дата текущая</UncontrolledTooltip>
              </AvGroup>
              <AvGroup check>
                <Label id="isWinnerLabel">
                  <AvInput
                    id="members-trade-deal-isWinner"
                    data-cy="isWinner"
                    type="checkbox"
                    className="form-check-input"
                    name="isWinner"
                  />
                  Is Winner
                </Label>
                <UncontrolledTooltip target="isWinnerLabel">победитель?</UncontrolledTooltip>
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput
                    id="members-trade-deal-isDelete"
                    data-cy="isDelete"
                    type="checkbox"
                    className="form-check-input"
                    name="isDelete"
                  />
                  Is Delete
                </Label>
                <UncontrolledTooltip target="isDeleteLabel">удаленный</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="members-trade-deal-date1">
                  Date 1
                </Label>
                <AvInput
                  id="members-trade-deal-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.membersTradeDealEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="members-trade-deal-date2">
                  Date 2
                </Label>
                <AvInput
                  id="members-trade-deal-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.membersTradeDealEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="members-trade-deal-long1">
                  Long 1
                </Label>
                <AvField id="members-trade-deal-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="members-trade-deal-string1">
                  String 1
                </Label>
                <AvField id="members-trade-deal-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput
                    id="members-trade-deal-boolean1"
                    data-cy="boolean1"
                    type="checkbox"
                    className="form-check-input"
                    name="boolean1"
                  />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="members-trade-deal-tradeShop">Trade Shop</Label>
                <AvInput id="members-trade-deal-tradeShop" data-cy="tradeShop" type="select" className="form-control" name="tradeShopId">
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
              <Button tag={Link} id="cancel-save" to="/members-trade-deal" replace color="info">
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
  membersTradeDealEntity: storeState.membersTradeDeal.entity,
  loading: storeState.membersTradeDeal.loading,
  updating: storeState.membersTradeDeal.updating,
  updateSuccess: storeState.membersTradeDeal.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(MembersTradeDealUpdate);
