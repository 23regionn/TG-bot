import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './rel-category-city-channels.reducer';
import { IRelCategoryCityChannels } from 'app/shared/model/rel-category-city-channels.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRelCategoryCityChannelsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RelCategoryCityChannelsUpdate = (props: IRelCategoryCityChannelsUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { relCategoryCityChannelsEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/rel-category-city-channels');
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
        ...relCategoryCityChannelsEntity,
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
          <h2 id="gidApp.relCategoryCityChannels.home.createOrEditLabel" data-cy="RelCategoryCityChannelsCreateUpdateHeading">
            Create or edit a RelCategoryCityChannels
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : relCategoryCityChannelsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="rel-category-city-channels-id">ID</Label>
                  <AvInput id="rel-category-city-channels-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="scoreChannelLabel" for="rel-category-city-channels-scoreChannel">
                  Score Channel
                </Label>
                <AvField
                  id="rel-category-city-channels-scoreChannel"
                  data-cy="scoreChannel"
                  type="string"
                  className="form-control"
                  name="scoreChannel"
                />
              </AvGroup>
              <AvGroup check>
                <Label id="isShowChannelLabel">
                  <AvInput
                    id="rel-category-city-channels-isShowChannel"
                    data-cy="isShowChannel"
                    type="checkbox"
                    className="form-check-input"
                    name="isShowChannel"
                  />
                  Is Show Channel
                </Label>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/rel-category-city-channels" replace color="info">
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
  relCategoryCityChannelsEntity: storeState.relCategoryCityChannels.entity,
  loading: storeState.relCategoryCityChannels.loading,
  updating: storeState.relCategoryCityChannels.updating,
  updateSuccess: storeState.relCategoryCityChannels.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RelCategoryCityChannelsUpdate);
