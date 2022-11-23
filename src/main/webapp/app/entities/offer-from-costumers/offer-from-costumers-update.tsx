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
import { getEntity, updateEntity, createEntity, reset } from './offer-from-costumers.reducer';
import { IOfferFromCostumers } from 'app/shared/model/offer-from-costumers.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOfferFromCostumersUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OfferFromCostumersUpdate = (props: IOfferFromCostumersUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { offerFromCostumersEntity, tGUsers, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/offer-from-costumers');
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
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...offerFromCostumersEntity,
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
          <h2 id="gidApp.offerFromCostumers.home.createOrEditLabel" data-cy="OfferFromCostumersCreateUpdateHeading">
            Create or edit a OfferFromCostumers
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : offerFromCostumersEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="offer-from-costumers-id">ID</Label>
                  <AvInput id="offer-from-costumers-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="textLabel" for="offer-from-costumers-text">
                  Text
                </Label>
                <AvField id="offer-from-costumers-text" data-cy="text" type="text" name="text" />
                <UncontrolledTooltip target="textLabel">текст предложения, обратная связь</UncontrolledTooltip>
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput
                    id="offer-from-costumers-isDelete"
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
                <Label id="adminIdLabel" for="offer-from-costumers-adminId">
                  Admin Id
                </Label>
                <AvField id="offer-from-costumers-adminId" data-cy="adminId" type="string" className="form-control" name="adminId" />
                <UncontrolledTooltip target="adminIdLabel">ссылка на админа, который пропустил отзыв</UncontrolledTooltip>
              </AvGroup>
              <AvGroup check>
                <Label id="isActiveLabel">
                  <AvInput
                    id="offer-from-costumers-isActive"
                    data-cy="isActive"
                    type="checkbox"
                    className="form-check-input"
                    name="isActive"
                  />
                  Is Active
                </Label>
                <UncontrolledTooltip target="isActiveLabel">предложение = обработали или нет</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="offer-from-costumers-date1">
                  Date 1
                </Label>
                <AvInput
                  id="offer-from-costumers-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.offerFromCostumersEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="offer-from-costumers-date2">
                  Date 2
                </Label>
                <AvInput
                  id="offer-from-costumers-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.offerFromCostumersEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="offer-from-costumers-long1">
                  Long 1
                </Label>
                <AvField id="offer-from-costumers-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="offer-from-costumers-string1">
                  String 1
                </Label>
                <AvField id="offer-from-costumers-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput
                    id="offer-from-costumers-boolean1"
                    data-cy="boolean1"
                    type="checkbox"
                    className="form-check-input"
                    name="boolean1"
                  />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="offer-from-costumers-tGUser">T G User</Label>
                <AvInput id="offer-from-costumers-tGUser" data-cy="tGUser" type="select" className="form-control" name="tGUserId">
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
              <Button tag={Link} id="cancel-save" to="/offer-from-costumers" replace color="info">
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
  offerFromCostumersEntity: storeState.offerFromCostumers.entity,
  loading: storeState.offerFromCostumers.loading,
  updating: storeState.offerFromCostumers.updating,
  updateSuccess: storeState.offerFromCostumers.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(OfferFromCostumersUpdate);
