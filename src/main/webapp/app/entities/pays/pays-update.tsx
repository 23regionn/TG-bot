import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITGUser } from 'app/shared/model/tg-user.model';
import { getEntities as getTGUsers } from 'app/entities/tg-user/tg-user.reducer';
import { getEntity, updateEntity, createEntity, reset } from './pays.reducer';
import { IPays } from 'app/shared/model/pays.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPaysUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PaysUpdate = (props: IPaysUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { paysEntity, tGUsers, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/pays');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTGUsers();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.datePaysSubscriptions = convertDateTimeToServer(values.datePaysSubscriptions);
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...paysEntity,
        ...values,
        tGUser: tGUsers.find(it => it.id.toString() === values.tGUserId.toString()),
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
          <h2 id="gidApp.pays.home.createOrEditLabel" data-cy="PaysCreateUpdateHeading">
            Create or edit a Pays
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : paysEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="pays-id">ID</Label>
                  <AvInput id="pays-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="datePaysSubscriptionsLabel" for="pays-datePaysSubscriptions">
                  Date Pays Subscriptions
                </Label>
                <AvInput
                  id="pays-datePaysSubscriptions"
                  data-cy="datePaysSubscriptions"
                  type="datetime-local"
                  className="form-control"
                  name="datePaysSubscriptions"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.paysEntity.datePaysSubscriptions)}
                />
                <UncontrolledTooltip target="datePaysSubscriptionsLabel">дата оплаты подписки</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="linkLabel" for="pays-link">
                  Link
                </Label>
                <AvField id="pays-link" data-cy="link" type="text" name="link" />
                <UncontrolledTooltip target="linkLabel">за какой канал он оплатил, за ссылку на какой канал</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="sumForPaysLabel" for="pays-sumForPays">
                  Sum For Pays
                </Label>
                <AvField id="pays-sumForPays" data-cy="sumForPays" type="string" className="form-control" name="sumForPays" />
                <UncontrolledTooltip target="sumForPaysLabel">сумма оплаты</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="typeBuyLabel" for="pays-typeBuy">
                  Type Buy
                </Label>
                <AvField id="pays-typeBuy" data-cy="typeBuy" type="text" name="typeBuy" />
                <UncontrolledTooltip target="typeBuyLabel">тип покупки</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="categoryLabel" for="pays-category">
                  Category
                </Label>
                <AvField id="pays-category" data-cy="category" type="text" name="category" />
                <UncontrolledTooltip target="categoryLabel">категория</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="typeBuyLongLabel" for="pays-typeBuyLong">
                  Type Buy Long
                </Label>
                <AvField id="pays-typeBuyLong" data-cy="typeBuyLong" type="string" className="form-control" name="typeBuyLong" />
                <UncontrolledTooltip target="typeBuyLongLabel">тип покупки long</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="pays-date1">
                  Date 1
                </Label>
                <AvInput
                  id="pays-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.paysEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="pays-date2">
                  Date 2
                </Label>
                <AvInput
                  id="pays-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.paysEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="pays-long1">
                  Long 1
                </Label>
                <AvField id="pays-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="pays-string1">
                  String 1
                </Label>
                <AvField id="pays-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="pays-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="pays-tGUser">T G User</Label>
                <AvInput id="pays-tGUser" data-cy="tGUser" type="select" className="form-control" name="tGUserId">
                  <option value="" key="0" />
                  {tGUsers
                    ? tGUsers.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/pays" replace color="info">
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
  tGUsers: storeState.tGUser.entities,
  paysEntity: storeState.pays.entity,
  loading: storeState.pays.loading,
  updating: storeState.pays.updating,
  updateSuccess: storeState.pays.updateSuccess,
});

const mapDispatchToProps = {
  getTGUsers,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PaysUpdate);
