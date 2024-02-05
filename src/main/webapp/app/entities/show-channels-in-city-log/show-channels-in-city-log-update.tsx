import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './show-channels-in-city-log.reducer';
import { IShowChannelsInCityLog } from 'app/shared/model/show-channels-in-city-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IShowChannelsInCityLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ShowChannelsInCityLogUpdate = (props: IShowChannelsInCityLogUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { showChannelsInCityLogEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/show-channels-in-city-log');
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
        ...showChannelsInCityLogEntity,
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
          <h2 id="gidApp.showChannelsInCityLog.home.createOrEditLabel" data-cy="ShowChannelsInCityLogCreateUpdateHeading">
            Create or edit a ShowChannelsInCityLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : showChannelsInCityLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="show-channels-in-city-log-id">ID</Label>
                  <AvInput id="show-channels-in-city-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="idChannelLabel" for="show-channels-in-city-log-idChannel">
                  Id Channel
                </Label>
                <AvField
                  id="show-channels-in-city-log-idChannel"
                  data-cy="idChannel"
                  type="string"
                  className="form-control"
                  name="idChannel"
                />
              </AvGroup>
              <AvGroup>
                <Label id="nameChannelLabel" for="show-channels-in-city-log-nameChannel">
                  Name Channel
                </Label>
                <AvField id="show-channels-in-city-log-nameChannel" data-cy="nameChannel" type="text" name="nameChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="idCategoryLabel" for="show-channels-in-city-log-idCategory">
                  Id Category
                </Label>
                <AvField
                  id="show-channels-in-city-log-idCategory"
                  data-cy="idCategory"
                  type="string"
                  className="form-control"
                  name="idCategory"
                />
              </AvGroup>
              <AvGroup>
                <Label id="nameCategoryLabel" for="show-channels-in-city-log-nameCategory">
                  Name Category
                </Label>
                <AvField id="show-channels-in-city-log-nameCategory" data-cy="nameCategory" type="text" name="nameCategory" />
              </AvGroup>
              <AvGroup>
                <Label id="idCityLabel" for="show-channels-in-city-log-idCity">
                  Id City
                </Label>
                <AvField id="show-channels-in-city-log-idCity" data-cy="idCity" type="string" className="form-control" name="idCity" />
              </AvGroup>
              <AvGroup>
                <Label id="nameCityLabel" for="show-channels-in-city-log-nameCity">
                  Name City
                </Label>
                <AvField id="show-channels-in-city-log-nameCity" data-cy="nameCity" type="text" name="nameCity" />
              </AvGroup>
              <AvGroup check>
                <Label id="isShowChannelLabel">
                  <AvInput
                    id="show-channels-in-city-log-isShowChannel"
                    data-cy="isShowChannel"
                    type="checkbox"
                    className="form-check-input"
                    name="isShowChannel"
                  />
                  Is Show Channel
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="scoreChannelLabel" for="show-channels-in-city-log-scoreChannel">
                  Score Channel
                </Label>
                <AvField
                  id="show-channels-in-city-log-scoreChannel"
                  data-cy="scoreChannel"
                  type="string"
                  className="form-control"
                  name="scoreChannel"
                />
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="show-channels-in-city-log-comment">
                  Comment
                </Label>
                <AvField id="show-channels-in-city-log-comment" data-cy="comment" type="text" name="comment" />
              </AvGroup>
              <AvGroup>
                <Label id="dateLogLabel" for="show-channels-in-city-log-dateLog">
                  Date Log
                </Label>
                <AvField id="show-channels-in-city-log-dateLog" data-cy="dateLog" type="date" className="form-control" name="dateLog" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/show-channels-in-city-log" replace color="info">
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
  showChannelsInCityLogEntity: storeState.showChannelsInCityLog.entity,
  loading: storeState.showChannelsInCityLog.loading,
  updating: storeState.showChannelsInCityLog.updating,
  updateSuccess: storeState.showChannelsInCityLog.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ShowChannelsInCityLogUpdate);
