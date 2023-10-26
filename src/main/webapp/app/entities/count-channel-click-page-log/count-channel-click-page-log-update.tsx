import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './count-channel-click-page-log.reducer';
import { ICountChannelClickPageLog } from 'app/shared/model/count-channel-click-page-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICountChannelClickPageLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CountChannelClickPageLogUpdate = (props: ICountChannelClickPageLogUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { countChannelClickPageLogEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/count-channel-click-page-log');
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
    values.dateLog = convertDateTimeToServer(values.dateLog);

    if (errors.length === 0) {
      const entity = {
        ...countChannelClickPageLogEntity,
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
          <h2 id="gidApp.countChannelClickPageLog.home.createOrEditLabel" data-cy="CountChannelClickPageLogCreateUpdateHeading">
            Create or edit a CountChannelClickPageLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : countChannelClickPageLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="count-channel-click-page-log-id">ID</Label>
                  <AvInput id="count-channel-click-page-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="chatIdLabel" for="count-channel-click-page-log-chatId">
                  Chat Id
                </Label>
                <AvField id="count-channel-click-page-log-chatId" data-cy="chatId" type="string" className="form-control" name="chatId" />
              </AvGroup>
              <AvGroup>
                <Label id="dateLogLabel" for="count-channel-click-page-log-dateLog">
                  Date Log
                </Label>
                <AvInput
                  id="count-channel-click-page-log-dateLog"
                  data-cy="dateLog"
                  type="datetime-local"
                  className="form-control"
                  name="dateLog"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.countChannelClickPageLogEntity.dateLog)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idChannelLabel" for="count-channel-click-page-log-idChannel">
                  Id Channel
                </Label>
                <AvField
                  id="count-channel-click-page-log-idChannel"
                  data-cy="idChannel"
                  type="string"
                  className="form-control"
                  name="idChannel"
                />
              </AvGroup>
              <AvGroup>
                <Label id="pageNumberLabel" for="count-channel-click-page-log-pageNumber">
                  Page Number
                </Label>
                <AvField
                  id="count-channel-click-page-log-pageNumber"
                  data-cy="pageNumber"
                  type="string"
                  className="form-control"
                  name="pageNumber"
                />
              </AvGroup>
              <AvGroup>
                <Label id="idCategoryLabel" for="count-channel-click-page-log-idCategory">
                  Id Category
                </Label>
                <AvField
                  id="count-channel-click-page-log-idCategory"
                  data-cy="idCategory"
                  type="string"
                  className="form-control"
                  name="idCategory"
                />
              </AvGroup>
              <AvGroup>
                <Label id="idCityLabel" for="count-channel-click-page-log-idCity">
                  Id City
                </Label>
                <AvField id="count-channel-click-page-log-idCity" data-cy="idCity" type="string" className="form-control" name="idCity" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/count-channel-click-page-log" replace color="info">
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
  countChannelClickPageLogEntity: storeState.countChannelClickPageLog.entity,
  loading: storeState.countChannelClickPageLog.loading,
  updating: storeState.countChannelClickPageLog.updating,
  updateSuccess: storeState.countChannelClickPageLog.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CountChannelClickPageLogUpdate);
