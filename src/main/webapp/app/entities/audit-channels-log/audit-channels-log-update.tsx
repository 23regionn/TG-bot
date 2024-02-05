import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './audit-channels-log.reducer';
import { IAuditChannelsLog } from 'app/shared/model/audit-channels-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAuditChannelsLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AuditChannelsLogUpdate = (props: IAuditChannelsLogUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { auditChannelsLogEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/audit-channels-log');
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
    values.endPublicDate = convertDateTimeToServer(values.endPublicDate);
    values.lastPayDate = convertDateTimeToServer(values.lastPayDate);
    values.startDate = convertDateTimeToServer(values.startDate);

    if (errors.length === 0) {
      const entity = {
        ...auditChannelsLogEntity,
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
          <h2 id="gidApp.auditChannelsLog.home.createOrEditLabel" data-cy="AuditChannelsLogCreateUpdateHeading">
            Create or edit a AuditChannelsLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : auditChannelsLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="audit-channels-log-id">ID</Label>
                  <AvInput id="audit-channels-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateLogLabel" for="audit-channels-log-dateLog">
                  Date Log
                </Label>
                <AvField id="audit-channels-log-dateLog" data-cy="dateLog" type="date" className="form-control" name="dateLog" />
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="audit-channels-log-comment">
                  Comment
                </Label>
                <AvField id="audit-channels-log-comment" data-cy="comment" type="text" name="comment" />
              </AvGroup>
              <AvGroup>
                <Label id="contactsLabel" for="audit-channels-log-contacts">
                  Contacts
                </Label>
                <AvField id="audit-channels-log-contacts" data-cy="contacts" type="text" name="contacts" />
              </AvGroup>
              <AvGroup>
                <Label id="endPublicDateLabel" for="audit-channels-log-endPublicDate">
                  End Public Date
                </Label>
                <AvInput
                  id="audit-channels-log-endPublicDate"
                  data-cy="endPublicDate"
                  type="datetime-local"
                  className="form-control"
                  name="endPublicDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.auditChannelsLogEntity.endPublicDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idChannelLabel" for="audit-channels-log-idChannel">
                  Id Channel
                </Label>
                <AvField id="audit-channels-log-idChannel" data-cy="idChannel" type="string" className="form-control" name="idChannel" />
              </AvGroup>
              <AvGroup check>
                <Label id="isModerateLabel">
                  <AvInput
                    id="audit-channels-log-isModerate"
                    data-cy="isModerate"
                    type="checkbox"
                    className="form-check-input"
                    name="isModerate"
                  />
                  Is Moderate
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="isPayLabel">
                  <AvInput id="audit-channels-log-isPay" data-cy="isPay" type="checkbox" className="form-check-input" name="isPay" />
                  Is Pay
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="lastPayDateLabel" for="audit-channels-log-lastPayDate">
                  Last Pay Date
                </Label>
                <AvInput
                  id="audit-channels-log-lastPayDate"
                  data-cy="lastPayDate"
                  type="datetime-local"
                  className="form-control"
                  name="lastPayDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.auditChannelsLogEntity.lastPayDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="linkLabel" for="audit-channels-log-link">
                  Link
                </Label>
                <AvField id="audit-channels-log-link" data-cy="link" type="text" name="link" />
              </AvGroup>
              <AvGroup>
                <Label id="nameChannelLabel" for="audit-channels-log-nameChannel">
                  Name Channel
                </Label>
                <AvField id="audit-channels-log-nameChannel" data-cy="nameChannel" type="text" name="nameChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="priceForPayLabel" for="audit-channels-log-priceForPay">
                  Price For Pay
                </Label>
                <AvField id="audit-channels-log-priceForPay" data-cy="priceForPay" type="text" name="priceForPay" />
              </AvGroup>
              <AvGroup>
                <Label id="startDateLabel" for="audit-channels-log-startDate">
                  Start Date
                </Label>
                <AvInput
                  id="audit-channels-log-startDate"
                  data-cy="startDate"
                  type="datetime-local"
                  className="form-control"
                  name="startDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.auditChannelsLogEntity.startDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="countSubscribersLabel" for="audit-channels-log-countSubscribers">
                  Count Subscribers
                </Label>
                <AvField
                  id="audit-channels-log-countSubscribers"
                  data-cy="countSubscribers"
                  type="string"
                  className="form-control"
                  name="countSubscribers"
                />
              </AvGroup>
              <AvGroup>
                <Label id="countViewsLabel" for="audit-channels-log-countViews">
                  Count Views
                </Label>
                <AvField id="audit-channels-log-countViews" data-cy="countViews" type="string" className="form-control" name="countViews" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/audit-channels-log" replace color="info">
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
  auditChannelsLogEntity: storeState.auditChannelsLog.entity,
  loading: storeState.auditChannelsLog.loading,
  updating: storeState.auditChannelsLog.updating,
  updateSuccess: storeState.auditChannelsLog.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AuditChannelsLogUpdate);
