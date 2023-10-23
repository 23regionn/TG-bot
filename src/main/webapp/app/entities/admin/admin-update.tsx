import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './admin.reducer';
import { IAdmin } from 'app/shared/model/admin.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdminUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdminUpdate = (props: IAdminUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { adminEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/admin');
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
    if (errors.length === 0) {
      const entity = {
        ...adminEntity,
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
          <h2 id="gidApp.admin.home.createOrEditLabel" data-cy="AdminCreateUpdateHeading">
            Create or edit a Admin
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : adminEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="admin-id">ID</Label>
                  <AvInput id="admin-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="admin-name">
                  Name
                </Label>
                <AvField id="admin-name" data-cy="name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="contactLabel" for="admin-contact">
                  Contact
                </Label>
                <AvField id="admin-contact" data-cy="contact" type="text" name="contact" />
              </AvGroup>
              <AvGroup>
                <Label id="linkLabel" for="admin-link">
                  Link
                </Label>
                <AvField id="admin-link" data-cy="link" type="text" name="link" />
              </AvGroup>
              <AvGroup>
                <Label id="scoreLabel" for="admin-score">
                  Score
                </Label>
                <AvField id="admin-score" data-cy="score" type="string" className="form-control" name="score" />
              </AvGroup>
              <AvGroup check>
                <Label id="isActiveLabel">
                  <AvInput id="admin-isActive" data-cy="isActive" type="checkbox" className="form-check-input" name="isActive" />
                  Is Active
                </Label>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/entity-admin" replace color="info">
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
  adminEntity: storeState.admin.entity,
  loading: storeState.admin.loading,
  updating: storeState.admin.updating,
  updateSuccess: storeState.admin.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdminUpdate);
