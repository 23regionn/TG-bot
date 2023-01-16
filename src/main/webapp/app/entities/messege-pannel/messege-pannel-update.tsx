import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './messege-pannel.reducer';
import { IMessegePannel } from 'app/shared/model/messege-pannel.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMessegePannelUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MessegePannelUpdate = (props: IMessegePannelUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { messegePannelEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/messege-pannel');
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
    values.dateCreateMessage = convertDateTimeToServer(values.dateCreateMessage);

    if (errors.length === 0) {
      const entity = {
        ...messegePannelEntity,
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
          <h2 id="gidApp.messegePannel.home.createOrEditLabel" data-cy="MessegePannelCreateUpdateHeading">
            Create or edit a MessegePannel
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : messegePannelEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="messege-pannel-id">ID</Label>
                  <AvInput id="messege-pannel-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="idMessageLabel" for="messege-pannel-idMessage">
                  Id Message
                </Label>
                <AvField id="messege-pannel-idMessage" data-cy="idMessage" type="string" className="form-control" name="idMessage" />
              </AvGroup>
              <AvGroup>
                <Label id="idChannelLabel" for="messege-pannel-idChannel">
                  Id Channel
                </Label>
                <AvField id="messege-pannel-idChannel" data-cy="idChannel" type="string" className="form-control" name="idChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreateMessageLabel" for="messege-pannel-dateCreateMessage">
                  Date Create Message
                </Label>
                <AvInput
                  id="messege-pannel-dateCreateMessage"
                  data-cy="dateCreateMessage"
                  type="datetime-local"
                  className="form-control"
                  name="dateCreateMessage"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.messegePannelEntity.dateCreateMessage)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="textMessageLabel" for="messege-pannel-textMessage">
                  Text Message
                </Label>
                <AvField id="messege-pannel-textMessage" data-cy="textMessage" type="text" name="textMessage" />
              </AvGroup>
              <AvGroup>
                <Label id="idAdminLabel" for="messege-pannel-idAdmin">
                  Id Admin
                </Label>
                <AvField id="messege-pannel-idAdmin" data-cy="idAdmin" type="string" className="form-control" name="idAdmin" />
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="messege-pannel-comment">
                  Comment
                </Label>
                <AvField id="messege-pannel-comment" data-cy="comment" type="text" name="comment" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="messege-pannel-status">
                  Status
                </Label>
                <AvField id="messege-pannel-status" data-cy="status" type="text" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceField1Label" for="messege-pannel-serviceField1">
                  Service Field 1
                </Label>
                <AvField id="messege-pannel-serviceField1" data-cy="serviceField1" type="text" name="serviceField1" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceField2Label" for="messege-pannel-serviceField2">
                  Service Field 2
                </Label>
                <AvField id="messege-pannel-serviceField2" data-cy="serviceField2" type="text" name="serviceField2" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceField3Label" for="messege-pannel-serviceField3">
                  Service Field 3
                </Label>
                <AvField id="messege-pannel-serviceField3" data-cy="serviceField3" type="text" name="serviceField3" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/messege-pannel" replace color="info">
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
  messegePannelEntity: storeState.messegePannel.entity,
  loading: storeState.messegePannel.loading,
  updating: storeState.messegePannel.updating,
  updateSuccess: storeState.messegePannel.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MessegePannelUpdate);
