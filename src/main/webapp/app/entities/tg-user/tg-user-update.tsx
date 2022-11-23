import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBalance } from 'app/shared/model/balance.model';
import { getEntities as getBalances } from 'app/entities/balance/balance.reducer';
import { getEntity, updateEntity, createEntity, reset } from './tg-user.reducer';
import { ITGUser } from 'app/shared/model/tg-user.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITGUserUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TGUserUpdate = (props: ITGUserUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { tGUserEntity, balances, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/tg-user');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getBalances();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.registrationDate = convertDateTimeToServer(values.registrationDate);
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...tGUserEntity,
        ...values,
        balance: balances.find(it => it.id.toString() === values.balanceId.toString()),
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
          <h2 id="gidApp.tGUser.home.createOrEditLabel" data-cy="TGUserCreateUpdateHeading">
            Create or edit a TGUser
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tGUserEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tg-user-id">ID</Label>
                  <AvInput id="tg-user-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="idTgUserLabel" for="tg-user-idTgUser">
                  Id Tg User
                </Label>
                <AvField id="tg-user-idTgUser" data-cy="idTgUser" type="string" className="form-control" name="idTgUser" />
              </AvGroup>
              <AvGroup>
                <Label id="firstNameLabel" for="tg-user-firstName">
                  First Name
                </Label>
                <AvField id="tg-user-firstName" data-cy="firstName" type="text" name="firstName" />
              </AvGroup>
              <AvGroup>
                <Label id="registrationDateLabel" for="tg-user-registrationDate">
                  Registration Date
                </Label>
                <AvInput
                  id="tg-user-registrationDate"
                  data-cy="registrationDate"
                  type="datetime-local"
                  className="form-control"
                  name="registrationDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.tGUserEntity.registrationDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="userRoleLabel" for="tg-user-userRole">
                  User Role
                </Label>
                <AvField id="tg-user-userRole" data-cy="userRole" type="text" name="userRole" />
              </AvGroup>
              <AvGroup check>
                <Label id="isAdminLabel">
                  <AvInput id="tg-user-isAdmin" data-cy="isAdmin" type="checkbox" className="form-check-input" name="isAdmin" />
                  Is Admin
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="scoreLabel" for="tg-user-score">
                  Score
                </Label>
                <AvField id="tg-user-score" data-cy="score" type="string" className="form-control" name="score" />
              </AvGroup>
              <AvGroup check>
                <Label id="isBlockedLabel">
                  <AvInput id="tg-user-isBlocked" data-cy="isBlocked" type="checkbox" className="form-check-input" name="isBlocked" />
                  Is Blocked
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="chatIdLabel" for="tg-user-chatId">
                  Chat Id
                </Label>
                <AvField id="tg-user-chatId" data-cy="chatId" type="string" className="form-control" name="chatId" />
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput id="tg-user-isDelete" data-cy="isDelete" type="checkbox" className="form-check-input" name="isDelete" />
                  Is Delete
                </Label>
                <UncontrolledTooltip target="isDeleteLabel">удаленный</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="tg-user-date1">
                  Date 1
                </Label>
                <AvInput
                  id="tg-user-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.tGUserEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="tg-user-date2">
                  Date 2
                </Label>
                <AvInput
                  id="tg-user-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.tGUserEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="tg-user-long1">
                  Long 1
                </Label>
                <AvField id="tg-user-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="tg-user-string1">
                  String 1
                </Label>
                <AvField id="tg-user-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="tg-user-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="tg-user-balance">Balance</Label>
                <AvInput id="tg-user-balance" data-cy="balance" type="select" className="form-control" name="balanceId">
                  <option value="" key="0" />
                  {balances
                    ? balances.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/tg-user" replace color="info">
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
  balances: storeState.balance.entities,
  tGUserEntity: storeState.tGUser.entity,
  loading: storeState.tGUser.loading,
  updating: storeState.tGUser.updating,
  updateSuccess: storeState.tGUser.updateSuccess,
});

const mapDispatchToProps = {
  getBalances,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TGUserUpdate);
