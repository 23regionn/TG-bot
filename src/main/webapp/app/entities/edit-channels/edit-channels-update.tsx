import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './edit-channels.reducer';
import { IEditChannels } from 'app/shared/model/edit-channels.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEditChannelsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EditChannelsUpdate = (props: IEditChannelsUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { editChannelsEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/edit-channels');
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
        ...editChannelsEntity,
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
          <h2 id="gidApp.editChannels.home.createOrEditLabel" data-cy="EditChannelsCreateUpdateHeading">
            Create or edit a EditChannels
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : editChannelsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="edit-channels-id">ID</Label>
                  <AvInput id="edit-channels-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="idMessageLabel" for="edit-channels-idMessage">
                  Id Message
                </Label>
                <AvField id="edit-channels-idMessage" data-cy="idMessage" type="string" className="form-control" name="idMessage" />
              </AvGroup>
              <AvGroup>
                <Label id="idChannelLabel" for="edit-channels-idChannel">
                  Id Channel
                </Label>
                <AvField id="edit-channels-idChannel" data-cy="idChannel" type="string" className="form-control" name="idChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreateMessageLabel" for="edit-channels-dateCreateMessage">
                  Date Create Message
                </Label>
                <AvInput
                  id="edit-channels-dateCreateMessage"
                  data-cy="dateCreateMessage"
                  type="datetime-local"
                  className="form-control"
                  name="dateCreateMessage"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.editChannelsEntity.dateCreateMessage)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameChannelLabel" for="edit-channels-lastNameChannel">
                  Last Name Channel
                </Label>
                <AvField id="edit-channels-lastNameChannel" data-cy="lastNameChannel" type="text" name="lastNameChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="newNameChannelLabel" for="edit-channels-newNameChannel">
                  New Name Channel
                </Label>
                <AvField id="edit-channels-newNameChannel" data-cy="newNameChannel" type="text" name="newNameChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="isAproveChangeLabel" for="edit-channels-isAproveChange">
                  Is Aprove Change
                </Label>
                <AvField id="edit-channels-isAproveChange" data-cy="isAproveChange" type="text" name="isAproveChange" />
              </AvGroup>
              <AvGroup>
                <Label id="lastLinkToChannelLabel" for="edit-channels-lastLinkToChannel">
                  Last Link To Channel
                </Label>
                <AvField id="edit-channels-lastLinkToChannel" data-cy="lastLinkToChannel" type="text" name="lastLinkToChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="newlastLinkToChannelLabel" for="edit-channels-newlastLinkToChannel">
                  Newlast Link To Channel
                </Label>
                <AvField id="edit-channels-newlastLinkToChannel" data-cy="newlastLinkToChannel" type="text" name="newlastLinkToChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="lastPriceChannelLabel" for="edit-channels-lastPriceChannel">
                  Last Price Channel
                </Label>
                <AvField
                  id="edit-channels-lastPriceChannel"
                  data-cy="lastPriceChannel"
                  type="string"
                  className="form-control"
                  name="lastPriceChannel"
                />
              </AvGroup>
              <AvGroup>
                <Label id="newPriceChannelLabel" for="edit-channels-newPriceChannel">
                  New Price Channel
                </Label>
                <AvField
                  id="edit-channels-newPriceChannel"
                  data-cy="newPriceChannel"
                  type="string"
                  className="form-control"
                  name="newPriceChannel"
                />
              </AvGroup>
              <AvGroup>
                <Label id="addDescriptionAboutChannelLabel" for="edit-channels-addDescriptionAboutChannel">
                  Add Description About Channel
                </Label>
                <AvField
                  id="edit-channels-addDescriptionAboutChannel"
                  data-cy="addDescriptionAboutChannel"
                  type="text"
                  name="addDescriptionAboutChannel"
                />
              </AvGroup>
              <AvGroup>
                <Label id="currentDescriptionChannelLabel" for="edit-channels-currentDescriptionChannel">
                  Current Description Channel
                </Label>
                <AvField
                  id="edit-channels-currentDescriptionChannel"
                  data-cy="currentDescriptionChannel"
                  type="text"
                  name="currentDescriptionChannel"
                />
              </AvGroup>
              <AvGroup>
                <Label id="addRegionChannelLabel" for="edit-channels-addRegionChannel">
                  Add Region Channel
                </Label>
                <AvField id="edit-channels-addRegionChannel" data-cy="addRegionChannel" type="text" name="addRegionChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="editRegionChannelLabel" for="edit-channels-editRegionChannel">
                  Edit Region Channel
                </Label>
                <AvField id="edit-channels-editRegionChannel" data-cy="editRegionChannel" type="text" name="editRegionChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="addCityChannelLabel" for="edit-channels-addCityChannel">
                  Add City Channel
                </Label>
                <AvField id="edit-channels-addCityChannel" data-cy="addCityChannel" type="text" name="addCityChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="editCityChannelLabel" for="edit-channels-editCityChannel">
                  Edit City Channel
                </Label>
                <AvField id="edit-channels-editCityChannel" data-cy="editCityChannel" type="text" name="editCityChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="userIdLabel" for="edit-channels-userId">
                  User Id
                </Label>
                <AvField id="edit-channels-userId" data-cy="userId" type="string" className="form-control" name="userId" />
              </AvGroup>
              <AvGroup>
                <Label id="userNameLabel" for="edit-channels-userName">
                  User Name
                </Label>
                <AvField id="edit-channels-userName" data-cy="userName" type="text" name="userName" />
              </AvGroup>
              <AvGroup check>
                <Label id="isApprovedChanhesLabel">
                  <AvInput
                    id="edit-channels-isApprovedChanhes"
                    data-cy="isApprovedChanhes"
                    type="checkbox"
                    className="form-check-input"
                    name="isApprovedChanhes"
                  />
                  Is Approved Chanhes
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="edit-channels-comment">
                  Comment
                </Label>
                <AvField id="edit-channels-comment" data-cy="comment" type="text" name="comment" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="edit-channels-status">
                  Status
                </Label>
                <AvField id="edit-channels-status" data-cy="status" type="text" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceField1Label" for="edit-channels-serviceField1">
                  Service Field 1
                </Label>
                <AvField id="edit-channels-serviceField1" data-cy="serviceField1" type="text" name="serviceField1" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceField2Label" for="edit-channels-serviceField2">
                  Service Field 2
                </Label>
                <AvField id="edit-channels-serviceField2" data-cy="serviceField2" type="text" name="serviceField2" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceField3Label" for="edit-channels-serviceField3">
                  Service Field 3
                </Label>
                <AvField id="edit-channels-serviceField3" data-cy="serviceField3" type="text" name="serviceField3" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/edit-channels" replace color="info">
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
  editChannelsEntity: storeState.editChannels.entity,
  loading: storeState.editChannels.loading,
  updating: storeState.editChannels.updating,
  updateSuccess: storeState.editChannels.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EditChannelsUpdate);
